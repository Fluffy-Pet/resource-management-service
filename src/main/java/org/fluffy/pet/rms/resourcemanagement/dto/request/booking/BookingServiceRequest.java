package org.fluffy.pet.rms.resourcemanagement.dto.request.booking;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BookingServiceRequest {
    @NotBlank
    private String serviceId;

    @Min(1)
    @NotNull
    private Integer quantity;

    @Valid
    @NotNull
    private BookingScheduleRequest bookingSchedule;

    @NotNull
    @Min(1)
    private Long amountInPaise;

    @NotNull
    @Min(0)
    private Integer discountBps;
}
