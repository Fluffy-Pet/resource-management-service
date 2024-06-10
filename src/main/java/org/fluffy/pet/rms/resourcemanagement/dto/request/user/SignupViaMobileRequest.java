package org.fluffy.pet.rms.resourcemanagement.dto.request.user;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.UserMobileRequest;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public final class SignupViaMobileRequest extends BaseUserSignupRequest {
    private UserMobileRequest userMobileRequest;
}
