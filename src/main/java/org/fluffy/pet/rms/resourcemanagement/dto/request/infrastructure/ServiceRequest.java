package org.fluffy.pet.rms.resourcemanagement.dto.request.infrastructure;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private PetCategory petCategory;
}
