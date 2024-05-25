package org.fluffy.pet.rms.resourcemanagement.dto.request.infrastructure;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class InfrastructureRequest {
    @NotBlank
    private String name;

    private String description;

    private List<@NotNull @Valid ServiceRequest> services;

    @NotBlank
    private String type;

    @NotBlank
    private String subType;
}
