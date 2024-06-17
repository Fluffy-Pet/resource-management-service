package org.fluffy.pet.rms.resourcemanagement.dto.response.booking;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BookingScheduleResponse {
    private LocalDate bookingDate;

    private LocalTime bookingTime;
}
