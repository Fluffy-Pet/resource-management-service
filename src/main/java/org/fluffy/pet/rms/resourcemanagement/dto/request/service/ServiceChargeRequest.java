package org.fluffy.pet.rms.resourcemanagement.dto.request.service;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceChargeRequest {
    private String chargeType;

    private Long amountInPaise;
}