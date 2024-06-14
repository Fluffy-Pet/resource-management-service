package org.fluffy.pet.rms.resourcemanagement.dto.request.service;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceImageRequest {
    private String url;

    private String imageDescription;
}
