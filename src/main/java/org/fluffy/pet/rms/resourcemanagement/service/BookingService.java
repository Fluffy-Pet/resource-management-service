package org.fluffy.pet.rms.resourcemanagement.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.fluffy.pet.rms.resourcemanagement.dto.request.booking.BookingRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.booking.BookingResponse;
import org.fluffy.pet.rms.resourcemanagement.util.PaginationWrapper;

import java.util.List;

public interface BookingService {
    BookingResponse getBooking(String bookingId);

    BookingResponse createBooking(BookingRequest bookingRequest);

    BookingResponse updateBooking(BookingRequest bookingRequest, String bookingId);

    void deleteBooking(String bookingId);

    PaginationWrapper<List<JsonNode>> filterBookings(FilterRequest filterRequest);
}
