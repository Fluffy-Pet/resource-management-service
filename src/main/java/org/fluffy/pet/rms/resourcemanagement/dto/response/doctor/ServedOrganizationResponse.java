package org.fluffy.pet.rms.resourcemanagement.dto.response.doctor;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.util.Constants;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServedOrganizationResponse {
    private String organizationName;

    private String role;

    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate startDate;

    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate endDate;
}
