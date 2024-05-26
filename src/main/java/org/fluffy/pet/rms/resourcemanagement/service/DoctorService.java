package org.fluffy.pet.rms.resourcemanagement.service;

import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.DoctorRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.doctor.DoctorResponse;

public interface DoctorService {
    DoctorResponse createDoctor(DoctorRequest doctorRequest);

    DoctorResponse getDoctor(String id);

    DoctorResponse updateDoctor(DoctorRequest updateDoctorRequest, String id);

    void deleteDoctor(String id);
}
