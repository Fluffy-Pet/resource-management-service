package org.fluffy.pet.rms.resourcemanagement.dto.request.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
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

    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate startDate;

    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate endDate;
}
