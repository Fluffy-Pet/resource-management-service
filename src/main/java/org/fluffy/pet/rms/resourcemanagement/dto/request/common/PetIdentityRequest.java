package org.fluffy.pet.rms.resourcemanagement.dto.request.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class PetIdentityRequest {
    private String id;
}
