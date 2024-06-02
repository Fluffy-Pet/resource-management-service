package org.fluffy.pet.rms.resourcemanagement.dto.request.volunteer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
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
    @Email
    private String email;

    @NotBlank
    private String mobile;

    @NotBlank
    private String password;

    private String firstName;

    private String lastName;

    private List<AvailabilityType> availability;

    private List<SkillType> skills;

    private List<@NotNull @Valid DocumentRequest> identityDocuments;

    @Valid
    private AddressRequest address;

    private List<@NotNull @Valid ServedOrganizationRequest> servedOrganizations;
}
