package org.fluffy.pet.rms.resourcemanagement.dto.response.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class EmailResponse {
    private String emailId;
}
