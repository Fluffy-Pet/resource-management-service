package org.fluffy.pet.rms.resourcemanagement.dto.response.common;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class PetIdentityResponse {
    private String petId;

    private String petName;

    private PetType petType;

    private String profilePicture;
}
