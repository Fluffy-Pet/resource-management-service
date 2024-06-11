package org.fluffy.pet.rms.resourcemanagement.dto.response.shelter;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.AddressResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.ContactInformationResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ShelterHomeResponse {
    private String name;

    private String description;

    private int capacity;

    private ContactInformationResponse contactInformation;

    private AddressResponse address;

    private String website;

    private List<PetType> acceptedPetTypes;

    private String adoptionInformation;
}
