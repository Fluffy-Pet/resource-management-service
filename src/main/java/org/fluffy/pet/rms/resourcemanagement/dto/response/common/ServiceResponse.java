package org.fluffy.pet.rms.resourcemanagement.dto.response.common;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;
import org.fluffy.pet.rms.resourcemanagement.enums.ServiceSubType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceResponse {
    private ServiceSubType serviceSubType;

    private PetType petType;
}
