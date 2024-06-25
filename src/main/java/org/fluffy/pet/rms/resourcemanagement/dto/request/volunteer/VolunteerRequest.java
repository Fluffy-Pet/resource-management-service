package org.fluffy.pet.rms.resourcemanagement.dto.request.volunteer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.annotations.NullOrNotBlank;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.AddressRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.IdentityDocumentRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.ServedOrganizationRequest;
import org.fluffy.pet.rms.resourcemanagement.enums.AvailabilityType;
import org.fluffy.pet.rms.resourcemanagement.enums.SkillType;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class VolunteerRequest {
    @NullOrNotBlank
    private String firstName;

    @NullOrNotBlank
    private String lastName;

    @NullOrNotBlank
    private String profileImageFileName;

    private List<@NotNull AvailabilityType> availability;

    private List<@NotNull SkillType> skills;

    private List<@NotNull @Valid IdentityDocumentRequest> identityDocuments;

    @Valid
    private AddressRequest address;

    private List<@NotNull @Valid ServedOrganizationRequest> servedOrganizations;
}
