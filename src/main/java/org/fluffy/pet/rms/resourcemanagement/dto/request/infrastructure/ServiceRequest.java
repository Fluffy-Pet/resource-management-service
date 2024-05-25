package org.fluffy.pet.rms.resourcemanagement.dto.request.infrastructure;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.PetCategory;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceRequest {
    private String serviceGroup;

    private String serviceSubGroup;

    private PetCategory petCategory;
}
