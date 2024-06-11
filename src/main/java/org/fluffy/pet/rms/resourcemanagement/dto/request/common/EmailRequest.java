package org.fluffy.pet.rms.resourcemanagement.dto.request.common;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class EmailRequest {
    @NotNull
    @Email
    private String emailId;
}
