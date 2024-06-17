package org.fluffy.pet.rms.resourcemanagement.dto.request.booking;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BookingServiceRequest {
    private String serviceId;

    private BookingScheduleRequest bookingSchedule;

    private Long amountInPaise;

    private Integer discountBps;
}
