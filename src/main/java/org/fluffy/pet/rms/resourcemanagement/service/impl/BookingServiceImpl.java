package org.fluffy.pet.rms.resourcemanagement.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.fluffy.pet.rms.resourcemanagement.dto.request.booking.BookingRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.booking.BookingResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.exception.AppException;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.helper.FilterHelper;
import org.fluffy.pet.rms.resourcemanagement.model.booking.Booking;
import org.fluffy.pet.rms.resourcemanagement.repository.BookingRepository;
import org.fluffy.pet.rms.resourcemanagement.service.BookingService;
import org.fluffy.pet.rms.resourcemanagement.transformer.BookingTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.PaginationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    private final BookingTransformer bookingTransformer;

    private final FilterHelper<BookingResponse> filterHelper;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, BookingTransformer bookingTransformer, FilterHelper<BookingResponse> filterHelper) {
        this.bookingRepository = bookingRepository;
        this.bookingTransformer = bookingTransformer;
        this.filterHelper = filterHelper;
    }

    @Override
    public BookingResponse getBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.BOOKING_NOT_FOUND))
        );
        return bookingTransformer.convertModelToResponse(booking);
    }

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        Booking booking = bookingTransformer.convertRequestToModel(bookingRequest);
        booking.setStatus(Status.ACTIVE);
        try {
            Booking createdBooking = bookingRepository.save(booking);
            return bookingTransformer.convertModelToResponse(createdBooking);
        } catch (DuplicateKeyException e) {
            throw new RestException(HttpStatus.BAD_REQUEST, ErrorResponse.from(ErrorCode.BOOKING_ALREADY_EXISTS));
        }
    }

    @Override
    public BookingResponse updateBooking(BookingRequest bookingRequest, String bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.BOOKING_NOT_FOUND))
        );
        bookingTransformer.updateBooking(booking, bookingRequest);
        Booking updatedBooking = bookingRepository.save(booking);
        return bookingTransformer.convertModelToResponse(updatedBooking);
    }

    @Override
    public void deleteBooking(String bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public PaginationWrapper<List<JsonNode>> filterBookings(FilterRequest filterRequest) {
        try {
            return filterHelper.filterEntities(
                    filterRequest,
                    this::convertModelToResponseFromFilterRequest
            );
        } catch (AppException appException) {
            ErrorResponse errorResponse = ErrorResponse.from(ErrorCode.INVALID_FILTER_REQUEST);
            errorResponse.setDetail(appException.getMessage());
            throw new RestException(HttpStatus.BAD_REQUEST, errorResponse);
        }
    }

    private Page<BookingResponse> convertModelToResponseFromFilterRequest(FilterRequest filterRequest) {
        Page<Booking> bookings = bookingRepository.filterDocuments(
                filterRequest.getFilters(),
                filterRequest.getSort(),
                filterRequest.getPage(),
                filterRequest.getPageSize()
        );
        return bookings.map(bookingTransformer::convertModelToResponse);
    }
}
