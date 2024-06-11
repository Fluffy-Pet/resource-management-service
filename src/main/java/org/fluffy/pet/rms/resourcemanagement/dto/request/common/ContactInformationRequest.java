package org.fluffy.pet.rms.resourcemanagement.dto.request.common;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ContactInformationRequest {
    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String emailAddress;
}