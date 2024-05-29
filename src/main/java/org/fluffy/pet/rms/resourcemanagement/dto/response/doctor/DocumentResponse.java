package org.fluffy.pet.rms.resourcemanagement.dto.response.doctor;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class DocumentResponse {
    private String idNumber;

    private String documentType;

    private String documentUrl;
}
