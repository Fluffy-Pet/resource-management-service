package org.fluffy.pet.rms.resourcemanagement.dto.request.doctor;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
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

    private List<PetType> specialization;

    @NotBlank
    private Double experience;

    private List<DocumentRequest> documents;

    private List<ClinicRequest> associatedClinics;

    @NotBlank
    private AddressRequest address;

    private List<ServedOrganizationRequest> servedOrganizations;

}
