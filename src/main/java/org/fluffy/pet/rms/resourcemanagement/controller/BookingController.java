package org.fluffy.pet.rms.resourcemanagement.controller;

import jakarta.validation.Valid;
import org.fluffy.pet.rms.resourcemanagement.dto.request.booking.BookingRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.booking.BookingResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ResponseWrapper;
import org.fluffy.pet.rms.resourcemanagement.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
