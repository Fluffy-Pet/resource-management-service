package org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaginationResponse(
        Integer currentPage,
        Integer pageSize,
        Integer numberOfRecords,
        Long totalRecords,
        Integer totalPages
) {
    public static PaginationResponse convertPageToPaginationResponse(Page<?> page) {
        Pageable pageable = page.getPageable();
        return PaginationResponse
                .builder()
                .currentPage(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .numberOfRecords(page.getNumberOfElements())
                .totalRecords(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
