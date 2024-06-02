package org.fluffy.pet.rms.resourcemanagement.dto.request.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.WorkingDays;
import org.fluffy.pet.rms.resourcemanagement.util.Constants;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperatingHoursRequest {
    @NotNull
    private List<WorkingDays> workingDays;

    @JsonFormat(pattern = Constants.DATE_FORMAT)
    @NotBlank
    private LocalTime startTime;

    @JsonFormat(pattern = Constants.DATE_FORMAT)
    @NotBlank
    private LocalTime endTime;
}
