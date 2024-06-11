package org.fluffy.pet.rms.resourcemanagement.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;

@Getter
@Setter
sealed abstract class BaseUserSignupRequest permits SignupViaEmailRequest, SignupViaMobileRequest {
    @NotNull
    private UserType userType;

    @NotBlank
    private String password;
}
