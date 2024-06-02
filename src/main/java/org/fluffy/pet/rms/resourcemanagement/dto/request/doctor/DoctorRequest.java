package org.fluffy.pet.rms.resourcemanagement.dto.request.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.request.clinic.ClinicRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.AddressRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.DocumentRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.ServedOrganizationRequest;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class DoctorRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String mobile;

    @NotBlank
    private String password;

    private String firstName;

    private String lastName;

    private List< PetType> specialization;

    private Double experience;

    private List<@NotNull @Valid DocumentRequest> documents;

    private List<@NotNull @Valid ClinicRequest> associatedClinics;

    @Valid
    private AddressRequest address;

    private List<@NotNull @Valid ServedOrganizationRequest> servedOrganizations;
}
