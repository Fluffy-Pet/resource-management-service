package org.fluffy.pet.rms.resourcemanagement.dto.response.volunteer;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.AddressResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.DocumentResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.ServedOrganizationResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.AvailabilityType;
import org.fluffy.pet.rms.resourcemanagement.enums.SkillType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class VolunteerResponse {
    private String firstName;

    private String lastName;

    private List<AvailabilityType> availability;

    private List<SkillType> skills;

    private List<DocumentResponse> identityDocuments;

    private AddressResponse address;

    private List<ServedOrganizationResponse> servedOrganizations;
}
