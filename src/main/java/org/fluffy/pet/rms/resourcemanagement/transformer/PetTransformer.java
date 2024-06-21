package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.pet.PetRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.pet.PetResponse;
import org.fluffy.pet.rms.resourcemanagement.model.animal.Owner;
import org.fluffy.pet.rms.resourcemanagement.model.animal.Pet;

@Transformer
public class PetTransformer {
    public PetResponse convertModelToResponse(Pet pet) {
        return PetResponse
                .builder()
                .name(pet.getName())
                .petType(pet.getPetType())
                .profileUrl(pet.getProfileImageFileName())
                .dateOfBirth(pet.getDateOfBirth())
                .build();
    }

    public Pet convertRequestToModel(PetRequest petRequest, String userId) {
        return Pet
                .builder()
                .name(petRequest.getName())
                .petType(petRequest.getPetType())
                .profileImageFileName(petRequest.getProfileUrl())
                .dateOfBirth(petRequest.getDateOfBirth())
                .owner(convertUserIdToOwner(userId))
                .build();
    }

    public Owner convertUserIdToOwner(String userId) {
        return Owner.builder().userId(userId).build();
    }

    public void updatePet(Pet pet, PetRequest petRequest) {
        pet.setName(petRequest.getName());
        pet.setPetType(petRequest.getPetType());
        pet.setProfileImageFileName(petRequest.getProfileUrl());
        pet.setDateOfBirth(petRequest.getDateOfBirth());
    }
}
