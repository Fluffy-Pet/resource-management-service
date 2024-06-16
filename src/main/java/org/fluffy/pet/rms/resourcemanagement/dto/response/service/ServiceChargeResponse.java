package org.fluffy.pet.rms.resourcemanagement.dto.response.service;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceChargeResponse {
    private String chargeType;

    private Long amountInPaise;
}
