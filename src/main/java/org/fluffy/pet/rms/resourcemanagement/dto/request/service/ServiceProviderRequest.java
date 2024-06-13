package org.fluffy.pet.rms.resourcemanagement.dto.request.service;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.ServiceProviderType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceProviderRequest {
    private ServiceProviderType providerType;

    private String providerId;
}
