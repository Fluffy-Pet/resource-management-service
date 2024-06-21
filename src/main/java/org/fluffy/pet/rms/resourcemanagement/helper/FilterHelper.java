package org.fluffy.pet.rms.resourcemanagement.helper;

import com.fasterxml.jackson.databind.JsonNode;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterRequest;
import org.fluffy.pet.rms.resourcemanagement.exception.AppException;
import org.fluffy.pet.rms.resourcemanagement.util.PaginationWrapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public interface FilterHelper<T> {
    PaginationWrapper<List<JsonNode>> filterEntities(
            FilterRequest filterRequest,
            Function<FilterRequest, Page<T>> fetchEntities
    ) throws AppException;
}
