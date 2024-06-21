package org.fluffy.pet.rms.resourcemanagement.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.bson.Document;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.ComparisonOperatorNode;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterNode;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.LogicalOperatorNode;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.SortCriteria;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

public final class FilterQueryUtils {
    public static <T> Page<T> getPaginatedDocuments(
            Class<T> documentClass,
            FilterNode filterNode,
            List<SortCriteria> sortCriteriaList,
            Integer page,
            Integer pageSize,
            ObjectMapper objectMapper,
            MongoTemplate mongoTemplate
    ) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Query filterQuery = createFilterQuery(filterNode, sortCriteriaList, objectMapper)
                .with(pageable);
        List<T> documents = mongoTemplate.find(filterQuery, documentClass);
        return PageableExecutionUtils.getPage(
                documents,
                pageable,
                () -> mongoTemplate.count(Query.of(filterQuery).limit(-1).skip(-1), documentClass)
        );
    }

    public static Query createFilterQuery(
            FilterNode filterNode,
            List<SortCriteria> sortCriteriaList,
            ObjectMapper objectMapper
    ) {
        Document queryDocument = transformFilterNodeToDatabaseQuery(filterNode, objectMapper);

        Document statusDocument = new Document();
        statusDocument.put(MongoConstants.OPR_EQ, Status.ACTIVE);
        queryDocument.put(MongoConstants.STATUS, statusDocument);

        Sort sort = transformSortCriteriaToSortQuery(sortCriteriaList);
        return new BasicQuery(queryDocument).with(sort);
    }

    @SneakyThrows
    public static Document transformFilterNodeToDatabaseQuery(FilterNode filterNode, ObjectMapper objectMapper) {
        Document rootDocument = new Document();
        return switch (filterNode) {
            case ComparisonOperatorNode comparisonOperatorNode -> {
                Object valueToSearch = objectMapper.convertValue(comparisonOperatorNode.getValue(), Object.class);
                Document expressionDocument = switch (comparisonOperatorNode.getOperator()) {
                    case GTE -> {
                        Document document = new Document();
                        document.put(MongoConstants.OPR_GTE, valueToSearch);
                        yield document;
                    }
                    case LTE -> {
                        Document document = new Document();
                        document.put(MongoConstants.OPR_LTE, valueToSearch);
                        yield document;
                    }
                    case EQUALS -> {
                        Document document = new Document();
                        document.put(MongoConstants.OPR_EQ, valueToSearch);
                        yield document;
                    }
                    case NOT_EQUALS -> {
                        Document document = new Document();
                        document.put(MongoConstants.OPR_NE, valueToSearch);
                        yield document;
                    }
                    case GT -> {
                        Document document = new Document();
                        document.put(MongoConstants.OPR_GT, valueToSearch);
                        yield document;
                    }
                    case LT -> {
                        Document document = new Document();
                        document.put(MongoConstants.OPR_LT, valueToSearch);
                        yield document;
                    }
                    case IN -> {
                        Document document = new Document();
                        document.put(MongoConstants.OPR_IN, valueToSearch);
                        yield document;
                    }
                    case NIN -> {
                        Document document = new Document();
                        document.put(MongoConstants.OPR_NIN, valueToSearch);
                        yield document;
                    }
                    case REGEX -> {
                        Document document = new Document();
                        document.put(
                                MongoConstants.OPR_REGEX,
                                String.format(MongoConstants.SEARCH_PATTERN, valueToSearch)
                        );
                        document.put(MongoConstants.OPTIONS, MongoConstants.CASE_INSENSITIVE_MATCHING_OPTION);
                        yield document;
                    }
                };
                rootDocument.put(comparisonOperatorNode.getField(), expressionDocument);
                yield rootDocument;
            }
            case LogicalOperatorNode logicalOperatorNode -> {
                if (logicalOperatorNode.getFields().isEmpty()) {
                    yield rootDocument;
                }
                final List<Document> nestedDocuments = logicalOperatorNode.getFields()
                        .stream()
                        .map(node -> transformFilterNodeToDatabaseQuery(node, objectMapper))
                        .toList();
                String operator = switch (logicalOperatorNode.getOperator()) {
                    case AND -> MongoConstants.OPR_AND;
                    case OR -> MongoConstants.OPR_OR;
                };
                rootDocument.put(operator, nestedDocuments);
                yield rootDocument;
            }
        };
    }

    public static Sort transformSortCriteriaToSortQuery(List<SortCriteria> sortCriteriaList) {
        Sort.Order[] orders = sortCriteriaList.stream()
                .map(sortCriteria -> {
                    Sort.Direction sortDirection = switch (sortCriteria.getSortDirection()) {
                        case ASC -> Sort.Direction.ASC;
                        case DESC -> Sort.Direction.DESC;
                    };
                    return new Sort.Order(
                            sortDirection,
                            sortCriteria.getField()
                    );
                })
                .toArray(Sort.Order[]::new);
        return Sort.by(orders);
    }

    private FilterQueryUtils() {
    }
}
