package org.fluffy.pet.rms.resourcemanagement.service.impl;

import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import lombok.extern.slf4j.Slf4j;
import org.fluffy.pet.rms.resourcemanagement.dto.request.infrastructure.InfrastructureRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.infrastructure.InfrastructureResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.model.infrastructure.Infrastructure;
import org.fluffy.pet.rms.resourcemanagement.repository.InfrastructureRepository;
import org.fluffy.pet.rms.resourcemanagement.service.InfrastructureService;
import org.fluffy.pet.rms.resourcemanagement.transformer.InfrastructureTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InfrastructureServiceImpl implements InfrastructureService {
    private final InfrastructureRepository infrastructureRepository;

    private final InfrastructureTransformer infrastructureTransformer;

    @Autowired
    public InfrastructureServiceImpl(InfrastructureRepository infrastructureRepository, InfrastructureTransformer infrastructureTransformer) {
        this.infrastructureRepository = infrastructureRepository;
        this.infrastructureTransformer = infrastructureTransformer;
    }

    @Override
    public InfrastructureResponse createInfrastructure(InfrastructureRequest createInfrastructureRequest) {
        Infrastructure infrastructure = infrastructureTransformer.convertRequestToModel(createInfrastructureRequest);
        infrastructure.setStatus(Status.ACTIVE);
        try {
            infrastructureRepository.save(infrastructure);
            return infrastructureTransformer.convertModelToResponse(infrastructure);
        } catch (ConditionalCheckFailedException e) {
            log.error(
                    String.format("Exception happened in creating infrastructure for %s", createInfrastructureRequest.getName()), e
            );
            throw new RestException(HttpStatus.BAD_REQUEST, ErrorResponse.from(ErrorCode.DUPLICATE_INFRASTRUCTURE));
        }
    }

    @Override
    public InfrastructureResponse getInfrastructure(String id) {
        Infrastructure infrastructure = infrastructureRepository.findById(id).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.INFRASTRUCTURE_NOT_FOUND))
        );
        return infrastructureTransformer.convertModelToResponse(infrastructure);
    }

    @Override
    public InfrastructureResponse updateInfrastructure(InfrastructureRequest updateInfrastructureRequest, String id) {
        Infrastructure infrastructure = infrastructureRepository.findById(id).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.INFRASTRUCTURE_NOT_FOUND))
        );
        infrastructureTransformer.updateInfrastructure(infrastructure, updateInfrastructureRequest);
        infrastructureRepository.save(infrastructure);
        return infrastructureTransformer.convertModelToResponse(infrastructure);
    }

    @Override
    public void deleteInfrastructure(String id) {
        infrastructureRepository.deleteById(id);
    }
}
