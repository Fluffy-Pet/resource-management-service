package org.fluffy.pet.rms.resourcemanagement.controller;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import org.fluffy.pet.rms.resourcemanagement.annotations.CheckAccess;
import org.fluffy.pet.rms.resourcemanagement.dto.request.booking.BookingRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.booking.BookingResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ResponseWrapper;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;
import org.fluffy.pet.rms.resourcemanagement.service.BookingService;
import org.fluffy.pet.rms.resourcemanagement.util.PaginationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/bookings")
@RestController
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<ResponseWrapper<BookingResponse>> getBooking(@PathVariable("bookingId") String bookingId) {
        BookingResponse booking = bookingService.getBooking(bookingId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(booking));
    }

    @PostMapping
    @CheckAccess(values = {UserType.CLIENT})
    public ResponseEntity<ResponseWrapper<BookingResponse>> createBooking(
            @RequestBody @Valid BookingRequest bookingRequest
    ) {
        BookingResponse booking = bookingService.createBooking(bookingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.success(booking));
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<ResponseWrapper<BookingResponse>> updateBooking(
            @RequestBody @Valid BookingRequest bookingRequest,
            @PathVariable("bookingId") String bookingId
    ) {
        BookingResponse booking = bookingService.updateBooking(bookingRequest, bookingId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(booking));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<ResponseWrapper<Void>> deleteBooking(
            @PathVariable("bookingId") String bookingId
    ) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(null));
    }

    @PostMapping("/filter")
    public ResponseEntity<ResponseWrapper<List<JsonNode>>> filterBookings(
            @RequestBody @Valid FilterRequest filterRequest
    ) {
        PaginationWrapper<List<JsonNode>> filterParentPaginationWrapper = bookingService.filterBookings(filterRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        ResponseWrapper.success(
                                filterParentPaginationWrapper.data(),
                                filterParentPaginationWrapper.paginationResponse()
                        )
                );
    }
}
