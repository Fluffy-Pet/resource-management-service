package org.fluffy.pet.rms.resourcemanagement.dto.response.doctor;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;
import org.fluffy.pet.rms.resourcemanagement.model.common.IdentityDocument;
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

    private List< PetType> specialization;

    private Double experience;

    private List<IdentityDocument> documents;

    private List<Clinic> associatedClinics;

    private Address address;

    private List<ServedOrganization> servedOrganizations;
}
