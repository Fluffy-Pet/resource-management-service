package org.fluffy.pet.rms.resourcemanagement.dto.response.infrastructure;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class InfrastructureResponse {
    private String name;

    private String description;

    private List<ServiceResponse> services;

    private String type;

    private String subType;
}
