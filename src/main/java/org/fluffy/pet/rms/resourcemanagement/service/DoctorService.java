package org.fluffy.pet.rms.resourcemanagement.service;

import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.DoctorRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.doctor.DoctorResponse;

public interface DoctorService {
    DoctorResponse getDoctor(String id);

    DoctorResponse updateCurrentDoctor(DoctorRequest updateDoctorRequest);

    void deleteDoctor(String id);
}
