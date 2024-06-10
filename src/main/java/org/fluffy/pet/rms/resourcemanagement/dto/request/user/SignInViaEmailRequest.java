package org.fluffy.pet.rms.resourcemanagement.dto.request.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.UserEmailRequest;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class SignInViaEmailRequest {
    @NotNull
    @Valid
    private UserEmailRequest userEmailRequest;

    @NotBlank
    private String password;

    @NotNull
    private UserType userType;
}
