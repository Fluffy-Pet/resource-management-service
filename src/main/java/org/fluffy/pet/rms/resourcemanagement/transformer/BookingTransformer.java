package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.booking.BookingRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.booking.BookingScheduleRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.booking.BookingServiceRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.booking.BookingResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.booking.BookingScheduleResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.booking.BookingServiceResponse;
import org.fluffy.pet.rms.resourcemanagement.model.booking.Booking;
import org.fluffy.pet.rms.resourcemanagement.model.booking.BookingSchedule;
import org.fluffy.pet.rms.resourcemanagement.model.booking.BookingService;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;

@Transformer
public class BookingTransformer {
    public BookingResponse convertModelToResponse(Booking booking) {
        return BookingResponse
                .builder()
                .bookingStatus(booking.getBookingStatus())
                .services(StreamUtils.emptyIfNull(booking.getServices()).map(this::convertModelToResponse).toList())
                .build();
    }

    public BookingServiceResponse convertModelToResponse(BookingService bookingService) {
        return BookingServiceResponse
                .builder()
                .serviceId(bookingService.getServiceId())
                .bookingSchedule(convertModelToResponse(bookingService.getBookingSchedule()))
                .amountInPaise(bookingService.getAmountInPaise())
                .discountBps(bookingService.getDiscountBps())
                .build();
    }

    public BookingScheduleResponse convertModelToResponse(BookingSchedule bookingSchedule) {
        return BookingScheduleResponse
                .builder()
                .bookingDate(bookingSchedule.getBookingDate())
                .bookingTime(bookingSchedule.getBookingTime())
                .build();
    }

    public Booking convertRequestToModel(BookingRequest bookingRequest) {
        return Booking
                .builder()
                .bookingStatus(bookingRequest.getBookingStatus())
                .services(StreamUtils.emptyIfNull(bookingRequest.getServices()).map(this::convertRequestToModel).toList())
                .build();
    }

    public BookingService convertRequestToModel(BookingServiceRequest bookingServiceRequest) {
        return BookingService
                .builder()
                .serviceId(bookingServiceRequest.getServiceId())
                .bookingSchedule(convertRequestToModel(bookingServiceRequest.getBookingSchedule()))
                .amountInPaise(bookingServiceRequest.getAmountInPaise())
                .discountBps(bookingServiceRequest.getDiscountBps())
                .build();
    }

    public BookingSchedule convertRequestToModel(BookingScheduleRequest bookingScheduleRequest) {
        return BookingSchedule
                .builder()
                .bookingDate(bookingScheduleRequest.getBookingDate())
                .bookingTime(bookingScheduleRequest.getBookingTime())
                .build();
    }

    public void updateBooking(Booking booking, BookingRequest bookingRequest) {
        booking.setBookingStatus(bookingRequest.getBookingStatus());
    }
}
