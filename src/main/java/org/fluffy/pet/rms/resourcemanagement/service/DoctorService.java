package org.fluffy.pet.rms.resourcemanagement.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.DoctorRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.doctor.DoctorResponse;
import org.fluffy.pet.rms.resourcemanagement.util.PaginationWrapper;

import java.util.List;

public interface DoctorService {
    DoctorResponse getDoctor(String id);

    DoctorResponse updateCurrentDoctor(DoctorRequest updateDoctorRequest);

    void deleteDoctor(String id);

    PaginationWrapper<List<JsonNode>> filterDoctors(FilterRequest filterRequest);
}
