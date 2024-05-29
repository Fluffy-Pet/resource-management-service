package org.fluffy.pet.rms.resourcemanagement.dto.request.doctor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServedOrganizationRequest {
    @NotBlank
    private String organizationName;

    @NotBlank
    private String role;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
