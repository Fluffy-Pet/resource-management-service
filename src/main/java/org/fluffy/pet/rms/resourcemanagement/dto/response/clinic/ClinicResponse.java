package org.fluffy.pet.rms.resourcemanagement.dto.response.clinic;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;
import org.fluffy.pet.rms.resourcemanagement.model.common.OperatingHours;
import org.fluffy.pet.rms.resourcemanagement.model.infrastructure.Service;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Builder
public class ClinicResponse {
    private String name;

    private Address address;

    private String phoneNumber;

    private OperatingHours openingHours;

    private List<Service> servicesOffered;
}
