package org.fluffy.pet.rms.resourcemanagement.service;

import org.fluffy.pet.rms.resourcemanagement.dto.request.booking.BookingRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.booking.BookingResponse;

public interface BookingService {
    BookingResponse getBooking(String bookingId);

    BookingResponse createBooking(BookingRequest bookingRequest);

    BookingResponse updateBooking(BookingRequest bookingRequest, String bookingId);

    void deleteBooking(String bookingId);
}
