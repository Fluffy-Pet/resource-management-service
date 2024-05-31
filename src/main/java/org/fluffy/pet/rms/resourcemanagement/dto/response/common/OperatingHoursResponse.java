package org.fluffy.pet.rms.resourcemanagement.dto.response.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.util.Constants;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OperatingHoursResponse {
    @JsonFormat(pattern = Constants.LOCAL_DATE_TIME_FORMAT)
    private LocalDateTime startTime;

    @JsonFormat(pattern = Constants.LOCAL_DATE_TIME_FORMAT)
    private LocalDateTime endTime;
}
