package org.fluffy.pet.rms.resourcemanagement.dto.response.common;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.DocumentType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class DocumentResponse {
    private String idNumber;

    private DocumentType documentType;

    private String documentUrl;
}
