package org.fluffy.pet.rms.resourcemanagement.dto.request.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.DurationType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceChargeRequest {
    @NotBlank
    private String chargeType;

    @NotNull
    @Min(1)
    private Long amountInPaise;

    @NotNull
    private DurationType durationType;
}
