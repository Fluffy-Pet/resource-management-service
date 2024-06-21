package org.fluffy.pet.rms.resourcemanagement.util;

import lombok.Builder;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.PaginationResponse;

@Builder
public record PaginationWrapper<T> (PaginationResponse paginationResponse, T data) {
}
