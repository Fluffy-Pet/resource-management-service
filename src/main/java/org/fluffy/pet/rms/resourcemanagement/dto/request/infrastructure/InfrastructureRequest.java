package org.fluffy.pet.rms.resourcemanagement.dto.request.infrastructure;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class InfrastructureRequest {
    private String name;

    private String description;

    private List<ServiceRequest> services;

    private String type;

    private String subType;
}
