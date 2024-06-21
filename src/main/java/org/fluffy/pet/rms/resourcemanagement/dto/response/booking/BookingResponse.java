package org.fluffy.pet.rms.resourcemanagement.dto.response.booking;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.BookingStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BookingResponse {
    private BookingStatus bookingStatus;

    private BookingServiceResponse service;
}
