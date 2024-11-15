package org.fluffy.pet.rms.resourcemanagement.dto.request.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.DocumentType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class IdentityDocumentRequest {
    @NotBlank
    private String idNumber;

    @NotNull
    private DocumentType documentType;

    @NotBlank
    private String documentFileName;
}
