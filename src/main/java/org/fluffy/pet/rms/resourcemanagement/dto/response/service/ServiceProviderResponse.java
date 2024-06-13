package org.fluffy.pet.rms.resourcemanagement.dto.response.service;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.ServiceProviderType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceProviderResponse {
    private ServiceProviderType providerType;

    private String providerId;
}
