package org.fluffy.pet.rms.resourcemanagement.dto.response.admin;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class AdminResponse {
    private String firstName;

    private String lastName;

    private String profileUrl;
}
