package org.fluffy.pet.rms.resourcemanagement.dto.response.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class SignInResponse {
    private String token;
}
