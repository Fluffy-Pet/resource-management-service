package org.fluffy.pet.rms.resourcemanagement.service;


import com.fasterxml.jackson.databind.JsonNode;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.shelter.ShelterHomeRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.shelter.ShelterHomeResponse;
import org.fluffy.pet.rms.resourcemanagement.util.PaginationWrapper;

import java.util.List;

public interface ShelterHomeService {
    ShelterHomeResponse createShelterHome(ShelterHomeRequest shelterHomeRequest);

    ShelterHomeResponse getShelterHome(String id);

    ShelterHomeResponse updateShelterHome(ShelterHomeRequest shelterHomeRequest, String id);

    void deleteShelterHome(String id);

    PaginationWrapper<List<JsonNode>> filterShelterHomes(FilterRequest filterRequest);
}