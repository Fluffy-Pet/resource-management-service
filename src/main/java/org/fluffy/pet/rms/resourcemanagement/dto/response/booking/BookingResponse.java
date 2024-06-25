package org.fluffy.pet.rms.resourcemanagement.dto.response.booking;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.PetIdentityResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.UserIdentityResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.BookingStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BookingResponse {
    private BookingStatus bookingStatus;

    private BookingServiceResponse service;

    private UserIdentityResponse userIdentity;

    private PetIdentityResponse petIdentity;
}
