package org.fluffy.pet.rms.resourcemanagement.service;

import org.fluffy.pet.rms.resourcemanagement.dto.request.clinic.ClinicRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.clinic.ClinicResponse;

public interface ClinicService {
    ClinicResponse createClinic(ClinicRequest clinicRequest);

    ClinicResponse getClinic(String id);

    ClinicResponse updateClinic(ClinicRequest updateClinicRequest, String id);

    void deleteClinic(String id);
}
