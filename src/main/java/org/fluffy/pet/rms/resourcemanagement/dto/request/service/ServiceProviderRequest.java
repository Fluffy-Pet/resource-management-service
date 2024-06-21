package org.fluffy.pet.rms.resourcemanagement.dto.request.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.ServiceProviderType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceProviderRequest {
    @NotNull
    private ServiceProviderType providerType;

    @NotBlank
    private String providerId;
}
