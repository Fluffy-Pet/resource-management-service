package org.fluffy.pet.rms.resourcemanagement.dto.request.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.annotations.NullOrNotBlank;
import org.fluffy.pet.rms.resourcemanagement.dto.request.clinic.ClinicRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.*;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class DoctorRequest<T> {
    @NotNull
    private T signupUserInfo;

    @NotNull
    private UserMobileRequest mobile;

    @NotBlank
    private String password;

    @NullOrNotBlank
    private String firstName;

    @NullOrNotBlank
    private String lastName;

    private List< PetType> specialization;

    private Double experience;

    private List<@NotNull @Valid DocumentRequest> documents;

    private List<@NotNull @Valid ClinicRequest> associatedClinics;

    @Valid
    private AddressRequest address;

    private List<@NotNull @Valid ServedOrganizationRequest> servedOrganizations;
}
