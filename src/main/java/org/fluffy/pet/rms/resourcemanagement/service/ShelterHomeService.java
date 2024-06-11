package org.fluffy.pet.rms.resourcemanagement.service;


import org.fluffy.pet.rms.resourcemanagement.dto.request.shelter.ShelterHomeRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.shelter.ShelterHomeResponse;

public interface ShelterHomeService {
    ShelterHomeResponse createShelterHome(ShelterHomeRequest shelterHomeRequest);

    ShelterHomeResponse getShelterHome(String id);

    ShelterHomeResponse updateShelterHome(ShelterHomeRequest shelterHomeRequest, String id);

    void deleteShelterHome(String id);
}
