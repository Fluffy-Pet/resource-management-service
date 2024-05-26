package org.fluffy.pet.rms.resourcemanagement.dto.response.doctor;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;
import org.fluffy.pet.rms.resourcemanagement.model.common.Document;
import org.fluffy.pet.rms.resourcemanagement.model.common.ServedOrganization;
import org.fluffy.pet.rms.resourcemanagement.model.infrastructure.Clinic;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class DoctorResponse {
    private String firstName;

    private String lastName;

    private List<PetType> specialization;

    private Double experience;

    private List<Document> documents;

    private List<Clinic> associatedClinics;

    private String address;

    private List<ServedOrganization> servedOrganizations;
}
