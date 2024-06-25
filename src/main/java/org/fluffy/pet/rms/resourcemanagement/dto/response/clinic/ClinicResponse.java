package org.fluffy.pet.rms.resourcemanagement.dto.response.clinic;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.AddressResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.OperatingHoursResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.UserIdentityResponse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Builder
public class ClinicResponse {
    private String clinicName;

    private String description;

    private AddressResponse address;

    private String profileImageUrl;

    private OperatingHoursResponse openingHours;

    private UserIdentityResponse owner;
}
