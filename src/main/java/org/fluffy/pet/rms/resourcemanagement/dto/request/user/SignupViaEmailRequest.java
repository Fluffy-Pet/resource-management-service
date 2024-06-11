package org.fluffy.pet.rms.resourcemanagement.dto.request.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.EmailRequest;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public final class SignupViaEmailRequest extends BaseUserSignupRequest {
    @NotNull
    @Valid
    private EmailRequest email;
}
