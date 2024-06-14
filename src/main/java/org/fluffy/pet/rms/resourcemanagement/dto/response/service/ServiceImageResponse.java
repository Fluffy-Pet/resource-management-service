package org.fluffy.pet.rms.resourcemanagement.dto.response.service;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceImageResponse {
    private String url;

    private String imageDescription;
}
