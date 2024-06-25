package org.fluffy.pet.rms.resourcemanagement.dto.request.common;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;
import org.fluffy.pet.rms.resourcemanagement.enums.ServiceSubType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceRequest {
    @NotNull
    private ServiceSubType serviceSubType;

    @NotNull
    private PetType petType;
}
