package org.fluffy.pet.rms.resourcemanagement.service;

import org.fluffy.pet.rms.resourcemanagement.dto.request.pet.PetRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.pet.PetResponse;

import java.util.List;

public interface PetService {
    PetResponse getPetById(String petId);

    PetResponse createPet(PetRequest petRequest);

    PetResponse updatePet(PetRequest petRequest, String petId);

    void deletePet(String petId);

    List<PetResponse> getOwnedPetsForCurrentUser();
}
