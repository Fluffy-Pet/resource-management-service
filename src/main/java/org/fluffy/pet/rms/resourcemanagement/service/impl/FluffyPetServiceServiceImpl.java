package org.fluffy.pet.rms.resourcemanagement.service.impl;

import org.fluffy.pet.rms.resourcemanagement.configuration.contexts.UserContext;
import org.fluffy.pet.rms.resourcemanagement.dto.request.service.FluffyPetServiceRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.service.FluffyPetServiceResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.helper.ClinicHelper;
import org.fluffy.pet.rms.resourcemanagement.helper.DoctorHelper;
import org.fluffy.pet.rms.resourcemanagement.helper.ShelterHomeHelper;
import org.fluffy.pet.rms.resourcemanagement.helper.VolunteerHelper;
import org.fluffy.pet.rms.resourcemanagement.model.common.ProviderIdentity;
import org.fluffy.pet.rms.resourcemanagement.model.service.FluffyPetService;
import org.fluffy.pet.rms.resourcemanagement.repository.FluffyPetServiceRepository;
import org.fluffy.pet.rms.resourcemanagement.service.FluffyPetServiceService;
import org.fluffy.pet.rms.resourcemanagement.transformer.FluffyPetServiceTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FluffyPetServiceServiceImpl implements FluffyPetServiceService {
    private final FluffyPetServiceRepository fluffyPetServiceRepository;

    private final FluffyPetServiceTransformer fluffyPetServiceTransformer;

    private final ClinicHelper clinicHelper;

    private final DoctorHelper doctorHelper;

    private final ShelterHomeHelper shelterHomeHelper;

    private final VolunteerHelper volunteerHelper;

    private final UserContext userContext;

    @Autowired
    public FluffyPetServiceServiceImpl(FluffyPetServiceRepository fluffyPetServiceRepository, FluffyPetServiceTransformer fluffyPetServiceTransformer, ClinicHelper clinicHelper, DoctorHelper doctorHelper, ShelterHomeHelper shelterHomeHelper, VolunteerHelper volunteerHelper, UserContext userContext) {
        this.fluffyPetServiceRepository = fluffyPetServiceRepository;
        this.fluffyPetServiceTransformer = fluffyPetServiceTransformer;
        this.clinicHelper = clinicHelper;
        this.doctorHelper = doctorHelper;
        this.shelterHomeHelper = shelterHomeHelper;
        this.volunteerHelper = volunteerHelper;
        this.userContext = userContext;
    }

    @Override
    public FluffyPetServiceResponse getService(String serviceId) {
        FluffyPetService fluffyPetService = fluffyPetServiceRepository.findById(serviceId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.SERVICE_NOT_FOUND))
        );
        return getServiceResponse(fluffyPetService);
    }

    @Override
    public FluffyPetServiceResponse createService(FluffyPetServiceRequest fluffyPetServiceRequest) {
        FluffyPetService fluffyPetService = fluffyPetServiceTransformer.convertRequestToModel(fluffyPetServiceRequest, userContext.getUserId());
        fluffyPetService.setId(UUID.randomUUID().toString());
        fluffyPetService.setStatus(Status.ACTIVE);
        try {
            FluffyPetService createdFluffyPetService = fluffyPetServiceRepository.save(fluffyPetService);
            return getServiceResponse(createdFluffyPetService);
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
        return getServiceResponse(updatedFluffyPetService);
    }

    @Override
    public void deleteService(String serviceId) {
        fluffyPetServiceRepository.deleteById(serviceId);
    }

    @Override
    public List<FluffyPetServiceResponse> getServiceForProvider(String providerId) {
        List<FluffyPetService> fluffyPetServices = fluffyPetServiceRepository.findByProviderId(providerId);
        return fluffyPetServices.stream().map(this::getServiceResponse).toList();
    }

    private FluffyPetServiceResponse getServiceResponse(FluffyPetService fluffyPetService) {
        ProviderIdentity providerIdentity = getProviderIdentityOrThrowException(fluffyPetService);
        return fluffyPetServiceTransformer.convertModelToResponse(fluffyPetService, providerIdentity);
    }

    private ProviderIdentity getProviderIdentityOrThrowException(FluffyPetService fluffyPetService) {
        String providerId = fluffyPetService.getProvider().getProviderId();
        Result<ProviderIdentity, ErrorCode> result = switch (fluffyPetService.getServiceProviderType()) {
            case CLINIC -> clinicHelper.getProviderIdentityById(providerId);
            case DOCTOR -> doctorHelper.getProviderIdentityById(providerId);
            case SHELTER_HOME -> shelterHomeHelper.getProviderIdentityById(providerId);
            case VOLUNTEER -> volunteerHelper.getProviderIdentityById(providerId);
        };
        if (result.isFailure()) {
            throw new RestException(HttpStatus.BAD_REQUEST, ErrorResponse.from(result.getError()));
        }
        return result.getData();
    }
}
