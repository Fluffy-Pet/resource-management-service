package org.fluffy.pet.rms.resourcemanagement.dto.response.service;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.ServiceSubType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FluffyPetServiceResponse {
    private ServiceSubType serviceSubType;

    private String description;

    private ProviderIdentityResponse providerIdentity;

    private List<ServiceChargeResponse> charges;
}
