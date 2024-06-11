package org.fluffy.pet.rms.resourcemanagement.dto.response.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ContactInformationResponse {
    private String phoneNumber;

    private String emailAddress;
}
