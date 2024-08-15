package org.fluffy.pet.rms.resourcemanagement.dto.request.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.ServiceProviderType;
import org.fluffy.pet.rms.resourcemanagement.enums.ServiceSubType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FluffyPetServiceRequest {
    @NotNull
    private ServiceSubType serviceSubType;

    @NotNull
    private ServiceProviderType serviceProviderType;

    @NotBlank
    private String description;

    @NotNull
    private List<@NotNull @Valid ServiceChargeRequest> charges;
}
