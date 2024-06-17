package org.fluffy.pet.rms.resourcemanagement.dto.request.booking;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BookingScheduleRequest {
    private LocalDate bookingDate;

    private LocalTime bookingTime;
}
