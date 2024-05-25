package org.fluffy.pet.rms.resourcemanagement.dto.request.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UserRequest {
    private String mobile;

    private String emailId;
}
