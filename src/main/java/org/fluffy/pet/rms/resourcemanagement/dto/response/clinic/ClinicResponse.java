package org.fluffy.pet.rms.resourcemanagement.dto.response.clinic;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;
import org.fluffy.pet.rms.resourcemanagement.model.infrastructure.Service;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Builder
public class ClinicResponse {
    @NotBlank
    private String name;

    @NotBlank
    private Address address;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String openingHours;

    @NotBlank
    private List<Service> servicesOffered;
}
