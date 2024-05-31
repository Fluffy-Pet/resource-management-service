package org.fluffy.pet.rms.resourcemanagement.dto.response.doctor;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class AddressResponse {
    private String street;

    private String city;

    private String state;

    private String country;

    private String zipCode;
}