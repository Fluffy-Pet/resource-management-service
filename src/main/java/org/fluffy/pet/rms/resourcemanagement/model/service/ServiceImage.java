package org.fluffy.pet.rms.resourcemanagement.model.service;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceImage {
    private String url;

    private String imageDescription;
}
