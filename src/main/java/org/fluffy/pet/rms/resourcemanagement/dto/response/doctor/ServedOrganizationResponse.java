package org.fluffy.pet.rms.resourcemanagement.dto.response.doctor;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServedOrganizationResponse {
    private String organizationName;

    private String role;

    private LocalDate startDate;

    private LocalDate endDate;
}
