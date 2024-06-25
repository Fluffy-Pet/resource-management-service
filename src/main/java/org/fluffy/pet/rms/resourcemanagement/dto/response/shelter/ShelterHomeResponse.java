package org.fluffy.pet.rms.resourcemanagement.dto.response.shelter;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.AddressResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.UserIdentityResponse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ShelterHomeResponse {
    private String name;

    private String description;

    private String profileImageUrl;

    private AddressResponse address;

    private UserIdentityResponse owner;
}
