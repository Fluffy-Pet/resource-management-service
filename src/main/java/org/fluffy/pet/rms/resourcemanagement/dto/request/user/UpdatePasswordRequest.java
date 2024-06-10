package org.fluffy.pet.rms.resourcemanagement.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class UpdatePasswordRequest {
    @NotBlank
    private String password;
}
