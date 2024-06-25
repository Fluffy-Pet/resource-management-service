package org.fluffy.pet.rms.resourcemanagement.dto.response.clinic;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.AddressResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.OperatingHoursResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.ServiceResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.UserIdentityResponse;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Builder
public class ClinicResponse {
    private String clinicName;

    private String description;

    private AddressResponse address;

    private OperatingHoursResponse openingHours;

    private List<ServiceResponse> servicesOffered;

    private UserIdentityResponse owner;
}
