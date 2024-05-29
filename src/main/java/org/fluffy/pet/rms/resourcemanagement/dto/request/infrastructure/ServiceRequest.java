package org.fluffy.pet.rms.resourcemanagement.dto.request.infrastructure;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.PetCategory;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceRequest {
    @NotBlank
    private String serviceGroup;

    @NotBlank
    private String serviceSubGroup;

    @NotNull
    private PetCategory petCategory;
}
