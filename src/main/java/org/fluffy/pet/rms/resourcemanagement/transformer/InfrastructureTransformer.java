package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.infrastructure.InfrastructureRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.infrastructure.ServiceRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.infrastructure.InfrastructureResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.infrastructure.ServiceResponse;
import org.fluffy.pet.rms.resourcemanagement.model.infrastructure.Infrastructure;
import org.fluffy.pet.rms.resourcemanagement.model.infrastructure.Service;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;

@Transformer
public class InfrastructureTransformer {
    public Service convertRequestToModel(ServiceRequest serviceRequest) {
        return Service
                .builder()
                .serviceGroup(serviceRequest.getServiceGroup())
                .serviceSubGroup(serviceRequest.getServiceSubGroup())
                .petCategory(serviceRequest.getPetCategory())
                .build();
    }

    public Infrastructure convertRequestToModel(InfrastructureRequest infrastructureRequest) {
        return Infrastructure
                .builder()
                .name(infrastructureRequest.getName())
                .description(infrastructureRequest.getDescription())
                .services(StreamUtils.emptyIfNull(infrastructureRequest.getServices()).map(this::convertRequestToModel).toList())
                .type(infrastructureRequest.getType())
                .subType(infrastructureRequest.getSubType())
                .build();
    }

    public void updateInfrastructure(Infrastructure infrastructure, InfrastructureRequest infrastructureRequest) {
        infrastructure.setName(infrastructureRequest.getName());
        infrastructure.setDescription(infrastructureRequest.getDescription());
        infrastructure.setType(infrastructureRequest.getType());
        infrastructure.setSubType(infrastructureRequest.getSubType());
    }

    public ServiceResponse convertModelToResponse(Service service) {
        return ServiceResponse
                .builder()
                .serviceGroup(service.getServiceGroup())
                .serviceSubGroup(service.getServiceSubGroup())
                .petCategory(service.getPetCategory())
                .build();
    }

    public InfrastructureResponse convertModelToResponse(Infrastructure infrastructure) {
        return InfrastructureResponse
                .builder()
                .name(infrastructure.getName())
                .description(infrastructure.getDescription())
                .services(StreamUtils.emptyIfNull(infrastructure.getServices()).map(this::convertModelToResponse).toList())
                .type(infrastructure.getType())
                .subType(infrastructure.getSubType())
                .build();
    }
}
