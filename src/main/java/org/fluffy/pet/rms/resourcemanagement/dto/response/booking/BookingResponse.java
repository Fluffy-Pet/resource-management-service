package org.fluffy.pet.rms.resourcemanagement.dto.response.booking;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.BookingStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BookingResponse {
    private BookingStatus bookingStatus;

    private List<BookingServiceResponse> services;
}
