package org.fluffy.pet.rms.resourcemanagement.dto.request.user;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.UserEmailRequest;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public final class SignupViaEmailRequest extends BaseUserSignupRequest {
    private UserEmailRequest userEmailRequest;
}
