package org.fluffy.pet.rms.resourcemanagement.dto.request.doctor;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.util.Constants;

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
    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate startDate;

    @NotNull
    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate endDate;
}
