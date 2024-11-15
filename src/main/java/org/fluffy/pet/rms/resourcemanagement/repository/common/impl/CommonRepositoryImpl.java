package org.fluffy.pet.rms.resourcemanagement.repository.common.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.result.UpdateResult;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterNode;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.SortCriteria;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.repository.common.CommonRepository;
import org.fluffy.pet.rms.resourcemanagement.util.FilterQueryUtils;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Optional;

public class CommonRepositoryImpl<T, ID> implements CommonRepository<T, ID> {
    private final MongoTemplate mongoTemplate;

    private final ObjectMapper objectMapper;

    private final Class<T> entityClass;

    public CommonRepositoryImpl(
            MongoTemplate mongoTemplate,
            ObjectMapper objectMapper,
            Class<T> entityClass
    ) {
        this.mongoTemplate = mongoTemplate;
        this.objectMapper = objectMapper;
        this.entityClass = entityClass;
    }

    @Override
    public T save(T entity) {
        return mongoTemplate.save(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        Query query = new Query(Criteria.where(MongoConstants.STATUS).is(Status.ACTIVE).and(MongoConstants.ID).is(id));
        return Optional.ofNullable(mongoTemplate.findOne(query, entityClass));
    }

    @Override
    public void deleteById(ID id) {
        Update update = Update.update(MongoConstants.STATUS, Status.INACTIVE);
        Query query = new Query(Criteria.where(MongoConstants.ID).is(id).and(MongoConstants.STATUS).is(Status.ACTIVE));
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, entityClass);
        updateResult.getModifiedCount();
    }

    @Override
    public List<T> findAllByIds(Iterable<ID> ids) {
        Query query = new Query(Criteria.where(MongoConstants.STATUS).is(Status.ACTIVE).and(MongoConstants.ID).in(ids));
        return mongoTemplate.find(query, entityClass);
    }

    @Override
    public boolean exists(String id) {
        Query query = new Query(Criteria.where(MongoConstants.STATUS).is(Status.ACTIVE).and(MongoConstants.ID).is(id));
        return mongoTemplate.exists(query, entityClass);
    }

    @Override
    public Page<T> filterDocuments(
            FilterNode filterNode,
            List<SortCriteria> sortCriteriaList,
            Integer page,
            Integer pageSize
    ) {
        return FilterQueryUtils.getPaginatedDocuments(
                entityClass,
                filterNode,
                sortCriteriaList,
                page,
                pageSize,
                objectMapper,
                mongoTemplate
        );
    }
}
