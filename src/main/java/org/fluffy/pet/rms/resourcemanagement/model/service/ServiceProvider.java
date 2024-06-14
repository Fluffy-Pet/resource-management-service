package org.fluffy.pet.rms.resourcemanagement.model.service;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.ServiceProviderType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceProvider {
    private ServiceProviderType providerType;

    private String providerId;
}
