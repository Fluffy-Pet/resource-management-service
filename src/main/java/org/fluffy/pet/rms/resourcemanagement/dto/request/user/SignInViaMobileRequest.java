package org.fluffy.pet.rms.resourcemanagement.dto.request.user;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.UserMobileRequest;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class SignInViaMobileRequest {
    private UserMobileRequest userMobileRequest;

    private String password;

    private UserType userType;
}
