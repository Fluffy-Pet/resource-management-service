package org.fluffy.pet.rms.resourcemanagement.dto.request.filter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fluffy.pet.rms.resourcemanagement.util.CommonUtils;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilterRequest {
    @Min(0)
    private Integer page = CommonUtils.PAGE;

    @Min(1)
    @Max(1000)
    private Integer pageSize = CommonUtils.PAGE_SIZE;

    private Set<@NotBlank String> fields;

    @Valid
    private FilterNode filters;

    private List<@Valid SortCriteria> sort;
}