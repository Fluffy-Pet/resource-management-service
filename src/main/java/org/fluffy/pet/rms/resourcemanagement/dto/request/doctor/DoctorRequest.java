package org.fluffy.pet.rms.resourcemanagement.dto.request.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.request.clinic.ClinicRequest;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class DoctorRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private List<PetType> specialization;

    @NotNull
    @Min(0)
    private Double experience;

    private List<@Valid @NotNull DocumentRequest> documents;

    private List<@Valid @NotNull ClinicRequest> associatedClinics;

    @Valid
    @NotNull
    private AddressRequest address;

    private List<@Valid @NotNull ServedOrganizationRequest> servedOrganizations;

}
