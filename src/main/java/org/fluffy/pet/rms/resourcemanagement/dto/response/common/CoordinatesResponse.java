package org.fluffy.pet.rms.resourcemanagement.dto.response.common;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CoordinatesResponse {
    private double latitude;

    private double longitude;
}
