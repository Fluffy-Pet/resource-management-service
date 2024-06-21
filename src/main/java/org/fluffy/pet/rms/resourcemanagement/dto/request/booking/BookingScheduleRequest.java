package org.fluffy.pet.rms.resourcemanagement.dto.request.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.util.Constants;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BookingScheduleRequest {
    @NotNull
    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate bookingStartDate;

    @NotNull
    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate bookingEndDate;

    @NotNull
    @JsonFormat(pattern = Constants.TIME_FORMAT)
    private LocalTime bookingTime;

    @NotNull
    @JsonFormat(pattern = Constants.TIME_FORMAT)
    private LocalTime bookingEndTime;
}
