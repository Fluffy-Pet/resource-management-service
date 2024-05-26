package org.fluffy.pet.rms.resourcemanagement.dto.request.doctor;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Builder
public class ClinicRequest {
    @NotBlank
    private String name;

    @NotBlank
    private Address address;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String operatingHours;

    @NotBlank
    private String servicesOffered;
}
