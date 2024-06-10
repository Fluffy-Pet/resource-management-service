package org.fluffy.pet.rms.resourcemanagement.dto.request.user;

import lombok.Getter;
import lombok.Setter;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;

@Getter
@Setter
sealed abstract class BaseUserSignupRequest permits SignupViaEmailRequest, SignupViaMobileRequest {
    private UserType userType;

    private String password;
}
