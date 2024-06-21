package org.fluffy.pet.rms.resourcemanagement.dto.response.doctor;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.clinic.ClinicResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.AddressResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.DocumentResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.ServedOrganizationResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class DoctorResponse {
    private String firstName;

    private String lastName;

    private String profileImageUrl;

    private List<PetType> specialization;

    private Double experience;

    private List<DocumentResponse> documents;

    private List<ClinicResponse> associatedClinics;

    private AddressResponse address;

    private List<ServedOrganizationResponse> servedOrganizations;
}
