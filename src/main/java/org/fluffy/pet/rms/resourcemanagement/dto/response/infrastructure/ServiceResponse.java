package org.fluffy.pet.rms.resourcemanagement.dto.response.infrastructure;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.PetCategory;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceResponse {
    private String serviceGroup;

    private String serviceSubGroup;

    private PetCategory petCategory;
}
