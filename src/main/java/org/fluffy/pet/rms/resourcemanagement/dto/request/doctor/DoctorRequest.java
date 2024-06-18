package org.fluffy.pet.rms.resourcemanagement.dto.request.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.annotations.NullOrNotBlank;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.AddressRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.AssociatedClinicRequest;
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
    @NullOrNotBlank
    private String firstName;

    @NullOrNotBlank
    private String lastName;

    private List<@NotNull PetType> specialization;

    private Double experience;

    private List<@NotNull @Valid DocumentRequest> documents;

    private List<@NotNull @Valid AssociatedClinicRequest> associatedClinics;

    @Valid
    private AddressRequest address;

    private List<@NotNull @Valid ServedOrganizationRequest> servedOrganizations;
}
