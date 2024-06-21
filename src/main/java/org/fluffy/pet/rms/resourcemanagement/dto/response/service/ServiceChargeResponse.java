package org.fluffy.pet.rms.resourcemanagement.dto.response.service;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.DurationType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceChargeResponse {
    private String chargeType;

    private Long amountInPaise;

    private DurationType durationType;
}
