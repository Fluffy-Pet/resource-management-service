package org.fluffy.pet.rms.resourcemanagement.dto.response.volunteer;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.ServedOrganizationResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.AvailabilityType;
import org.fluffy.pet.rms.resourcemanagement.enums.SkillType;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;
import org.fluffy.pet.rms.resourcemanagement.model.common.IdentityDocument;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class VoluteerResponse {
    private String firstName;

    private String lastName;

    private List<AvailabilityType> availability;

    private List<SkillType> skills;

    private List<IdentityDocument> identityDocuments;

    private Address address;

    private List<ServedOrganizationResponse> servedOrganizations;
}
