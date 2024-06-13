package org.fluffy.pet.rms.resourcemanagement.dto.response.service;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.ServiceType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceResponse {
    private ServiceType serviceType;

    private String description;

    private List<ServiceImageResponse> serviceImages;

    private ServiceProviderResponse provider;
}
