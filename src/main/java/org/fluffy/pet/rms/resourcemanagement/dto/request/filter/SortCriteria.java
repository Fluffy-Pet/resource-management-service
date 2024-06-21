package org.fluffy.pet.rms.resourcemanagement.dto.request.filter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fluffy.pet.rms.resourcemanagement.enums.SortDirection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SortCriteria {

    @NotBlank
    private String field;

    @NotNull
    private SortDirection sortDirection;
}
