package org.fluffy.pet.rms.resourcemanagement.dto.response.doctor;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class DocumentResponse {
    @NotBlank
    private String idNumber;

    @NotBlank
    private String documentType;

    @NotBlank
    private String documentUrl;
}
