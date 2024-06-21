package org.fluffy.pet.rms.resourcemanagement.model.booking;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BookingSchedule {
    private LocalDate bookingStartDate;

    private LocalTime bookingStartTime;

    private LocalDate bookingEndDate;

    private LocalTime bookingEndTime;
}
