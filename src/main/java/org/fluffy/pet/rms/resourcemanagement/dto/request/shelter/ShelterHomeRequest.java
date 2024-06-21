package org.fluffy.pet.rms.resourcemanagement.dto.request.shelter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.annotations.NullOrNotBlank;
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

    @NullOrNotBlank
    private String profileImageFileName;

    @Min(value = 0)
    @NotNull
    private Integer capacity;

    @NotNull
    @Valid
    private MobileRequest mobile;

    @NotNull
    @Valid
    private EmailRequest email;

    @NotNull
    @Valid
    private AddressRequest address;

    @NotNull
    private List<@NotNull PetType> acceptedPetTypes;

}