package org.fluffy.pet.rms.resourcemanagement.service;


import org.fluffy.pet.rms.resourcemanagement.dto.request.infrastructure.InfrastructureRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.infrastructure.InfrastructureResponse;

public interface InfrastructureService {
    InfrastructureResponse createInfrastructure(InfrastructureRequest createInfrastructureRequest);

    InfrastructureResponse getInfrastructure(String id);

    InfrastructureResponse updateInfrastructure(InfrastructureRequest updateInfrastructureRequest, String id);

    void deleteInfrastructure(String id);
}
