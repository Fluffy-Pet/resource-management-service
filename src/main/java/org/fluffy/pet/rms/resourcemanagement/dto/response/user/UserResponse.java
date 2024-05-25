package org.fluffy.pet.rms.resourcemanagement.dto.response.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UserResponse {
    private String mobile;

    private String emailId;
}
