package org.fluffy.pet.rms.resourcemanagement.dto.request.volunteer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.AddressRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.DocumentRequest;
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
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private List<@NotNull AvailabilityType> availability;

    private List<@NotNull SkillType> skills;

    private List<@Valid @NotNull DocumentRequest> identityDocuments;

    @Valid
    @NotNull
    private AddressRequest address;

    private List<@Valid @NotNull ServedOrganizationRequest> servedOrganizations;
}
