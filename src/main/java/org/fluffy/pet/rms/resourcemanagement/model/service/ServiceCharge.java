package org.fluffy.pet.rms.resourcemanagement.model.service;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.DurationType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceCharge {
    private String chargeType;

    private Long amountInPaise;

    private DurationType durationType;
}
