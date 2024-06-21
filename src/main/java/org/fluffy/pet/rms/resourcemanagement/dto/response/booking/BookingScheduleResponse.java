package org.fluffy.pet.rms.resourcemanagement.dto.response.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.util.Constants;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BookingScheduleResponse {
    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate bookingStartDate;

    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate bookingEndDate;

    @JsonFormat(pattern = Constants.TIME_FORMAT)
    private LocalTime bookingStartTime;

    @JsonFormat(pattern = Constants.TIME_FORMAT)
    private LocalTime bookingEndTime;
}
