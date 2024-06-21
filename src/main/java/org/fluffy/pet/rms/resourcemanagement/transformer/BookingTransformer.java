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
import org.fluffy.pet.rms.resourcemanagement.model.booking.UserInfo;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;

@Transformer
public class BookingTransformer {
    public BookingResponse convertModelToResponse(Booking booking) {
        return BookingResponse
                .builder()
                .bookingStatus(booking.getBookingStatus())
                .service(ObjectUtils.transformIfNotNull(booking.getService(), this::convertModelToResponse))
                .build();
    }

    public BookingServiceResponse convertModelToResponse(BookingService bookingService) {
        return BookingServiceResponse
                .builder()
                .serviceId(bookingService.getServiceId())
                .quantity(bookingService.getQuantity())
                .bookingSchedule(convertModelToResponse(bookingService.getBookingSchedule()))
                .amountInPaise(bookingService.getAmountInPaise())
                .discountBps(bookingService.getDiscountBps())
                .build();
    }

    public BookingScheduleResponse convertModelToResponse(BookingSchedule bookingSchedule) {
        return BookingScheduleResponse
                .builder()
                .bookingStartDate(bookingSchedule.getBookingStartDate())
                .bookingStartTime(bookingSchedule.getBookingStartTime())
                .bookingEndDate(bookingSchedule.getBookingEndDate())
                .bookingEndTime(bookingSchedule.getBookingEndTime())
                .build();
    }

    public Booking convertRequestToModel(BookingRequest bookingRequest) {
        return Booking
                .builder()
                .bookingStatus(bookingRequest.getBookingStatus())
                .service(ObjectUtils.transformIfNotNull(bookingRequest.getService(), this::convertRequestToModel))
                .build();
    }

    public UserInfo convertRequestToModel(String userId) {
        return UserInfo.builder().userId(userId).build();
    }

    public BookingService convertRequestToModel(BookingServiceRequest bookingServiceRequest) {
        return BookingService
                .builder()
                .serviceId(bookingServiceRequest.getServiceId())
                .quantity(bookingServiceRequest.getQuantity())
                .bookingSchedule(convertRequestToModel(bookingServiceRequest.getBookingSchedule()))
                .amountInPaise(bookingServiceRequest.getAmountInPaise())
                .discountBps(bookingServiceRequest.getDiscountBps())
                .build();
    }

    public BookingSchedule convertRequestToModel(BookingScheduleRequest bookingScheduleRequest) {
        return BookingSchedule
                .builder()
                .bookingStartDate(bookingScheduleRequest.getBookingStartDate())
                .bookingEndDate(bookingScheduleRequest.getBookingEndDate())
                .bookingStartTime(bookingScheduleRequest.getBookingTime())
                .bookingEndTime(bookingScheduleRequest.getBookingEndTime())
                .build();
    }

    public void updateBooking(Booking booking, BookingRequest bookingRequest) {
        booking.setBookingStatus(bookingRequest.getBookingStatus());
    }
}
