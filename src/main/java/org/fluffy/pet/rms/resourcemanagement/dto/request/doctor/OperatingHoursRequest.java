package org.fluffy.pet.rms.resourcemanagement.dto.request.doctor;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperatingHoursRequest {
    @NotBlank
    private LocalDateTime startTime;

    @NotBlank
    private LocalDateTime endTime;
}
