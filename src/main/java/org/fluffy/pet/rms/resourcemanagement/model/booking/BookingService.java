package org.fluffy.pet.rms.resourcemanagement.model.booking;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BookingService {
    private String serviceId;

    private BookingSchedule bookingSchedule;

    private Long amountInPaise;

    private Integer discountBps;
}
