package org.fluffy.pet.rms.resourcemanagement.dto.request.clinic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.AddressRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.OperatingHoursRequest;
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

    @Valid
    @NotNull
    private AddressRequest address;

    @NotBlank
    private String phoneNumber;

    @Valid
    @NotNull
    private OperatingHoursRequest operatingHours;

    private List<@Valid @NotNull ServiceRequest> servicesOffered;
}
