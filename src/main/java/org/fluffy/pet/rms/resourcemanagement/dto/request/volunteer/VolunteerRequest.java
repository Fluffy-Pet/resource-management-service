package org.fluffy.pet.rms.resourcemanagement.dto.request.volunteer;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.AddressRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.DocumentRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.ServedOrganizationRequest;
import org.fluffy.pet.rms.resourcemanagement.enums.AvailabilityType;
import org.fluffy.pet.rms.resourcemanagement.enums.SkillType;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class VolunteerRequest {
    private String firstName;

    private String lastName;

    private List<AvailabilityType> availability;

    private List<SkillType> skills;

    private List<DocumentRequest> identityDocuments;

    private AddressRequest address;

    private List<ServedOrganizationRequest> servedOrganizations;
}
