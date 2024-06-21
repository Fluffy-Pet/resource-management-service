package org.fluffy.pet.rms.resourcemanagement.dto.request.admin;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.annotations.NullOrNotBlank;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class AdminRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NullOrNotBlank
    private String profileImageFileName;
}
