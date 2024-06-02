package org.fluffy.pet.rms.resourcemanagement.dto.response.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.WorkingDays;
import org.fluffy.pet.rms.resourcemanagement.util.Constants;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OperatingHoursResponse {
    @NotNull
    private List<WorkingDays> workingDays;

    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalTime startTime;

    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalTime endTime;
}
