package org.fluffy.pet.rms.resourcemanagement.service;

import org.fluffy.pet.rms.resourcemanagement.dto.request.service.ServiceRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.service.ServiceResponse;

public interface ServiceService {
    ServiceResponse getService(String serviceId);

    ServiceResponse createService(ServiceRequest serviceRequest);
}
