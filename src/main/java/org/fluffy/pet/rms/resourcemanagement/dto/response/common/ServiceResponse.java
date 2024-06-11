package org.fluffy.pet.rms.resourcemanagement.dto.response.common;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceResponse {
    private String serviceGroup;

    private String serviceSubGroup;

    private PetType petType;
}
