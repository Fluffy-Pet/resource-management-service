package org.fluffy.pet.rms.resourcemanagement.dto.request.clinic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.annotations.NullOrNotBlank;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.AddressRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.OperatingHoursRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Builder
public class ClinicRequest {
    @NotBlank
    private String clinicName;

    @NotBlank
    private String description;

    @Valid
    @NotNull
    private AddressRequest address;

    @NullOrNotBlank
    private String profileImageFileName;

    @Valid
    @NotNull
    private OperatingHoursRequest operatingHours;
}
