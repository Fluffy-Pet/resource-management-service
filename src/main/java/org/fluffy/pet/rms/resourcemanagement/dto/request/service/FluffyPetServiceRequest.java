package org.fluffy.pet.rms.resourcemanagement.dto.request.service;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.ServiceType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FluffyPetServiceRequest {
    private ServiceType serviceType;

    private String description;

    private List<ServiceImageRequest> serviceImages;

    private ServiceProviderRequest provider;

    private List<ServiceChargeRequest> charges;
}
