package org.fluffy.pet.rms.resourcemanagement.dto.request.volunteer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.annotations.NullOrNotBlank;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.*;
import org.fluffy.pet.rms.resourcemanagement.enums.AvailabilityType;
import org.fluffy.pet.rms.resourcemanagement.enums.SkillType;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class VolunteerRequest {
    @NotNull
    private MobileRequest mobile;

    @NotBlank
    private String password;

    @NullOrNotBlank
    private String firstName;

    @NullOrNotBlank
    private String lastName;

    private List<@NotNull AvailabilityType> availability;

    private List<@NotNull  SkillType> skills;

    private List<@NotNull @Valid DocumentRequest> identityDocuments;

    @Valid
    private AddressRequest address;

    private List<@NotNull @Valid ServedOrganizationRequest> servedOrganizations;
}
