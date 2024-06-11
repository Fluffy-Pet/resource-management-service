package org.fluffy.pet.rms.resourcemanagement.dto.response.client;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class ClientResponse {
    private String firstName;

    private String lastName;

    private String profileUrl;
}
