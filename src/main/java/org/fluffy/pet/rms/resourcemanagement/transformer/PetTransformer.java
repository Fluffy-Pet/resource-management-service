package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.pet.PetRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.pet.PetResponse;
import org.fluffy.pet.rms.resourcemanagement.model.animal.Owner;
import org.fluffy.pet.rms.resourcemanagement.model.animal.Pet;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

@Transformer
public class PetTransformer {
    private final CommonTransformer commonTransformer;

    @Autowired
    public PetTransformer(CommonTransformer commonTransformer) {
        this.commonTransformer = commonTransformer;
    }

    public PetResponse convertModelToResponse(Pet pet) {
        return PetResponse
                .builder()
                .name(pet.getName())
                .petType(pet.getPetType())
                .profileUrl(
                        ObjectUtils.transformIfNotNull(
                                ObjectUtils.transformIfNotNull(
                                        pet.getProfileImageFileName(),
                                        commonTransformer::convertFileNameToUrl
                                ),
                                URL::toString
                        )
                )
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
