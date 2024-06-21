package org.fluffy.pet.rms.resourcemanagement.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.fluffy.pet.rms.resourcemanagement.dto.request.clinic.ClinicRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.clinic.ClinicResponse;
import org.fluffy.pet.rms.resourcemanagement.util.PaginationWrapper;

import java.util.List;

public interface ClinicService {
    ClinicResponse createClinic(ClinicRequest clinicRequest);

    ClinicResponse getClinic(String id);

    ClinicResponse updateClinic(ClinicRequest updateClinicRequest, String id);

    void deleteClinic(String id);

    PaginationWrapper<List<JsonNode>> filterClinics(FilterRequest filterRequest);
}
