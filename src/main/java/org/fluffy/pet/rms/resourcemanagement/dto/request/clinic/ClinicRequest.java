package org.fluffy.pet.rms.resourcemanagement.dto.request.clinic;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.AddressRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.infrastructure.ServiceRequest;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Builder
public class ClinicRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private AddressRequest address;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String operatingHours;

    @NotBlank
    private List<ServiceRequest> servicesOffered;
}
