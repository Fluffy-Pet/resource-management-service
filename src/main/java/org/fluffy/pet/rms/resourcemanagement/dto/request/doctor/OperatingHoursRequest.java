package org.fluffy.pet.rms.resourcemanagement.dto.request.doctor;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperatingHoursRequest {
    @NotBlank
    private String startTime;

    @NotBlank
    private String endTime;
}
