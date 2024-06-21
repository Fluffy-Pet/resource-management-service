package org.fluffy.pet.rms.resourcemanagement.dto.response.booking;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BookingServiceResponse {
    private String serviceId;

    private Integer quantity;

    private BookingScheduleResponse bookingSchedule;

    private Long amountInPaise;

    private Integer discountBps;
}
