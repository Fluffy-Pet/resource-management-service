package org.fluffy.pet.rms.resourcemanagement.helper.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fluffy.pet.rms.resourcemanagement.annotations.Helper;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.PaginationResponse;
import org.fluffy.pet.rms.resourcemanagement.exception.AppException;
import org.fluffy.pet.rms.resourcemanagement.helper.FilterHelper;
import org.fluffy.pet.rms.resourcemanagement.util.JsonUtils;
import org.fluffy.pet.rms.resourcemanagement.util.PaginationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

@Helper
public class FilterHelperImpl<T> implements FilterHelper<T> {
    private final ObjectMapper objectMapper;

    @Autowired
    public FilterHelperImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public PaginationWrapper<List<JsonNode>> filterEntities(FilterRequest filterRequest, Function<FilterRequest, Page<T>> fetchEntities) throws AppException {
        Page<T> entities = fetchEntities.apply(filterRequest);
        Page<JsonNode> jsonNodes = entities.map(response -> objectMapper.convertValue(response, JsonNode.class));
        Page<JsonNode> objectNodes = jsonNodes.map(
                jsonNode -> JsonUtils.extractResultFromJsonNode(jsonNode, filterRequest.getFields(), objectMapper)
        );
        PaginationResponse paginationResponse = PaginationResponse.convertPageToPaginationResponse(objectNodes);
        return new PaginationWrapper<>(paginationResponse, objectNodes.getContent());
    }
}
