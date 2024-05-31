package org.fluffy.pet.rms.resourcemanagement.dto.request.common;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CoordinatesRequest {
    @NotNull
    @Min(0)
    private double latitude;

    @NotNull
    @Min(0)
    private double longitude;
}
