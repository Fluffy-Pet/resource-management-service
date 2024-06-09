package org.fluffy.pet.rms.resourcemanagement.dto.response.clinic;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.AddressResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.OperatingHoursResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.ServiceResponse;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Builder
public class ClinicResponse {
    private String name;

    private AddressResponse address;

    private String phoneNumber;

    private OperatingHoursResponse openingHours;

    private List<ServiceResponse> servicesOffered;
}
