package org.fluffy.pet.rms.resourcemanagement.dto.request.booking;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.BookingStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BookingRequest {
    private BookingStatus bookingStatus;

    private List<BookingServiceRequest> services;
}
