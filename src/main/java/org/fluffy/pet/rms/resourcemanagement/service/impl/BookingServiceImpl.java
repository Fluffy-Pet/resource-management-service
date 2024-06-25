package org.fluffy.pet.rms.resourcemanagement.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.fluffy.pet.rms.resourcemanagement.configuration.contexts.UserContext;
import org.fluffy.pet.rms.resourcemanagement.dto.request.booking.BookingRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.booking.BookingResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;
import org.fluffy.pet.rms.resourcemanagement.exception.AppException;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.helper.FilterHelper;
import org.fluffy.pet.rms.resourcemanagement.helper.PetHelper;
import org.fluffy.pet.rms.resourcemanagement.helper.UserHelper;
import org.fluffy.pet.rms.resourcemanagement.model.animal.Owner;
import org.fluffy.pet.rms.resourcemanagement.model.animal.Pet;
import org.fluffy.pet.rms.resourcemanagement.model.booking.Booking;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserIdentity;
import org.fluffy.pet.rms.resourcemanagement.repository.BookingRepository;
import org.fluffy.pet.rms.resourcemanagement.service.BookingService;
import org.fluffy.pet.rms.resourcemanagement.transformer.BookingTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.PaginationWrapper;
import org.fluffy.pet.rms.resourcemanagement.util.Result;
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

    private final PetHelper petHelper;

    private final UserHelper userHelper;

    private final UserContext userContext;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, BookingTransformer bookingTransformer, FilterHelper<BookingResponse> filterHelper, PetHelper petHelper, UserHelper userHelper, UserContext userContext) {
        this.bookingRepository = bookingRepository;
        this.bookingTransformer = bookingTransformer;
        this.filterHelper = filterHelper;
        this.petHelper = petHelper;
        this.userHelper = userHelper;
        this.userContext = userContext;
    }

    @Override
    public BookingResponse getBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.BOOKING_NOT_FOUND))
        );
        return getBookingResponse(booking);
    }

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        Result<Owner, ErrorCode> petOwnerResult = petHelper.findPetOwner(bookingRequest.getPetIdentity().getId());
        if (petOwnerResult.isFailure()) {
            throw new RestException(HttpStatus.BAD_REQUEST, ErrorResponse.from(petOwnerResult.getError()));
        }
        if (!petOwnerResult.getData().getUserId().equals(userContext.getUserId())) {
            throw new RestException(HttpStatus.BAD_REQUEST, ErrorResponse.from(ErrorCode.PET_OWNER_MIS_MATCH));
        }
        Booking booking = bookingTransformer.convertRequestToModel(bookingRequest);
        booking.setUserInfo(bookingTransformer.convertRequestToModel(userContext.getUserId()));
        booking.setStatus(Status.ACTIVE);
        try {
            Booking createdBooking = bookingRepository.save(booking);
            return getBookingResponse(createdBooking);
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
        return getBookingResponse(updatedBooking);
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
        return bookings.map(this::getBookingResponse);
    }

    private BookingResponse getBookingResponse(Booking booking) {
        Result<Pet, ErrorCode> petByIdResult = petHelper.findPetById(booking.getUserInfo().getPetId());
        if (petByIdResult.isFailure()) {
            throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorResponse.from(petByIdResult.getError()));
        }
        Result<UserIdentity, ErrorCode> userIdentityResult = userHelper.getUserIdentity(booking.getUserInfo().getUserId(), UserType.CLIENT);
        if (userIdentityResult.isFailure()) {
            throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorResponse.from(userIdentityResult.getError()));
        }
        return bookingTransformer.convertModelToResponse(booking, petByIdResult.getData(), userIdentityResult.getData());
    }
}
