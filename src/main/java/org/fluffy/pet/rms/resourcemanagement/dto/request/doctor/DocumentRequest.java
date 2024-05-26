package org.fluffy.pet.rms.resourcemanagement.dto.request.doctor;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.DocumentType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class DocumentRequest {
    @NotBlank
    private String idNumber;

    @NotBlank
    private DocumentType documentType;

    @NotBlank
    private String documentUrl;
}
