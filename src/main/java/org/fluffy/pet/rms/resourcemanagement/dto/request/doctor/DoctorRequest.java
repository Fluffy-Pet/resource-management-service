package org.fluffy.pet.rms.resourcemanagement.dto.request.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.annotations.NullOrNotBlank;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.AddressRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.AssociatedClinicRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.IdentityDocumentRequest;
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

    @NullOrNotBlank
    private String profileImageFileName;

    private List<@NotNull PetType> specialization;

    @Min(0)
    @Max(50)
    private Double experience;

    private List<@NotNull @Valid IdentityDocumentRequest> documents;

    private List<@NotNull @Valid AssociatedClinicRequest> associatedClinics;

    @Valid
    private AddressRequest address;

    private List<@NotNull @Valid ServedOrganizationRequest> servedOrganizations;
}
