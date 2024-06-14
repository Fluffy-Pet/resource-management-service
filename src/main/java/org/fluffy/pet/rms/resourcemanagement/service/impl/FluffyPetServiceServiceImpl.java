package org.fluffy.pet.rms.resourcemanagement.service.impl;

import org.fluffy.pet.rms.resourcemanagement.dto.request.service.FluffyPetServiceRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.service.FluffyPetServiceResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.model.service.FluffyPetService;
import org.fluffy.pet.rms.resourcemanagement.repository.FluffyPetServiceRepository;
import org.fluffy.pet.rms.resourcemanagement.service.FluffyPetServiceService;
import org.fluffy.pet.rms.resourcemanagement.transformer.FluffyPetServiceTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FluffyPetServiceServiceImpl implements FluffyPetServiceService {
    private final FluffyPetServiceRepository fluffyPetServiceRepository;

    private final FluffyPetServiceTransformer fluffyPetServiceTransformer;

    @Autowired
    public FluffyPetServiceServiceImpl(FluffyPetServiceRepository fluffyPetServiceRepository, FluffyPetServiceTransformer fluffyPetServiceTransformer) {
        this.fluffyPetServiceRepository = fluffyPetServiceRepository;
        this.fluffyPetServiceTransformer = fluffyPetServiceTransformer;
    }

    @Override
    public FluffyPetServiceResponse getService(String serviceId) {
        FluffyPetService fluffyPetService = fluffyPetServiceRepository.findById(serviceId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.SERVICE_NOT_FOUND))
        );
        return fluffyPetServiceTransformer.convertModelToResponse(fluffyPetService);
    }

    @Override
    public FluffyPetServiceResponse createService(FluffyPetServiceRequest fluffyPetServiceRequest) {
        FluffyPetService fluffyPetService = fluffyPetServiceTransformer.convertRequestToModel(fluffyPetServiceRequest);
        fluffyPetService.setId(UUID.randomUUID().toString());
        fluffyPetService.setStatus(Status.ACTIVE);
        try {
            FluffyPetService createdFluffyPetService = fluffyPetServiceRepository.save(fluffyPetService);
            return fluffyPetServiceTransformer.convertModelToResponse(createdFluffyPetService);
        } catch (DuplicateKeyException exception) {
            throw new RestException(HttpStatus.BAD_REQUEST, ErrorResponse.from(ErrorCode.SERVICE_ALREADY_EXISTS));
        }
    }

    @Override
    public FluffyPetServiceResponse updateService(FluffyPetServiceRequest fluffyPetServiceRequest, String serviceId) {
        FluffyPetService fluffyPetService = fluffyPetServiceRepository.findById(serviceId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.SERVICE_NOT_FOUND))
        );
        fluffyPetServiceTransformer.updateFluffyPetService(fluffyPetService, fluffyPetServiceRequest);
        FluffyPetService updatedFluffyPetService = fluffyPetServiceRepository.save(fluffyPetService);
        return fluffyPetServiceTransformer.convertModelToResponse(updatedFluffyPetService);
    }

    @Override
    public void deleteService(String serviceId) {
        fluffyPetServiceRepository.deleteById(serviceId);
    }
}
