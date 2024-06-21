package org.fluffy.pet.rms.resourcemanagement.dto.request.client;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.annotations.NullOrNotBlank;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class ClientRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NullOrNotBlank
    private String profileImageFileName;
}
