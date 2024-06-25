package org.fluffy.pet.rms.resourcemanagement.dto.response.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UserIdentityResponse {
    private String userId;

    private String profilePhoto;

    private String firstName;

    private String lastName;
}
