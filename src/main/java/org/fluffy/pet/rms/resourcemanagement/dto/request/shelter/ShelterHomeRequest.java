package org.fluffy.pet.rms.resourcemanagement.dto.request.shelter;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.AddressRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.EmailRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.MobileRequest;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ShelterHomeRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Min(value = 0)
    private int capacity;

    @NotNull
    private MobileRequest mobile;

    @NotNull
    private EmailRequest email;

    @NotNull
    private AddressRequest address;

    @NotBlank
    private String website;

    @NotNull
    private List<@NotNull PetType> acceptedPetTypes;

}