package org.fluffy.pet.rms.resourcemanagement.dto.request.booking;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.BookingStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BookingRequest {
    @NotNull
    private BookingStatus bookingStatus;

    @NotNull
    @Valid
    private BookingServiceRequest service;
}
