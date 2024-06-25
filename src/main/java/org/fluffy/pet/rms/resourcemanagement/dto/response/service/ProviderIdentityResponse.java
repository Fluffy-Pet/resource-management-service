package org.fluffy.pet.rms.resourcemanagement.dto.response.service;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ProviderIdentityResponse {
    private String providerId;

    private String name;

    private String profileUrl;
}
