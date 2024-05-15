package org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaginationResponse(
        Integer currentPage,
        Integer pageSize,
        Integer numberOfRecords,
        Long totalRecords,
        Integer totalPages
) {
}
