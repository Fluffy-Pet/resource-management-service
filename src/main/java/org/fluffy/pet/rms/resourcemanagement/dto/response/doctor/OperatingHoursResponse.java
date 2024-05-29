package org.fluffy.pet.rms.resourcemanagement.dto.response.doctor;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OperatingHoursResponse {
    private String startTime;

    private String endTime;
}
