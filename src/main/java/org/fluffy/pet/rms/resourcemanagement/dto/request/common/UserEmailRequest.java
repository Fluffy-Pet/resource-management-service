package org.fluffy.pet.rms.resourcemanagement.dto.request.common;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class UserEmailRequest {
    @NotBlank
    @Email
    private String email;
}
