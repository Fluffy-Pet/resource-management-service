package org.fluffy.pet.rms.resourcemanagement.service;

import org.fluffy.pet.rms.resourcemanagement.dto.request.service.FluffyPetServiceRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.service.FluffyPetServiceResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ServiceType;

import java.util.List;

public interface FluffyPetServiceService {
    FluffyPetServiceResponse getService(String serviceId);

    FluffyPetServiceResponse createService(FluffyPetServiceRequest fluffyPetServiceRequest);

    FluffyPetServiceResponse updateService(FluffyPetServiceRequest fluffyPetServiceRequest, String serviceId);

    void deleteService(String serviceId);

    List<FluffyPetServiceResponse> getServiceForProvider(String providerId);

    List<FluffyPetServiceResponse> getServiceForServiceType(String serviceType);
}
