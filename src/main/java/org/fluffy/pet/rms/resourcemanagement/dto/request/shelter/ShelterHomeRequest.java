package org.fluffy.pet.rms.resourcemanagement.dto.request.shelter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.annotations.NullOrNotBlank;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.AddressRequest;

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

    @NotNull
    @Valid
    private AddressRequest address;
}