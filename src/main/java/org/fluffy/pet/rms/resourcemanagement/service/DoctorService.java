package org.fluffy.pet.rms.resourcemanagement.service;

import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.DoctorRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.doctor.DoctorResponse;

public interface DoctorService {
    DoctorResponse getDoctor(String id);

    <T> DoctorResponse updateDoctor(DoctorRequest<T> updateDoctorRequest, String id);

    void deleteDoctor(String id);
}
