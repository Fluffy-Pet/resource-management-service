package org.fluffy.pet.rms.resourcemanagement.service.impl;

import org.fluffy.pet.rms.resourcemanagement.configuration.contexts.UserContext;
import org.fluffy.pet.rms.resourcemanagement.dto.request.pet.PetRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.pet.PetResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.model.animal.Pet;
import org.fluffy.pet.rms.resourcemanagement.repository.PetRepository;
import org.fluffy.pet.rms.resourcemanagement.service.PetService;
import org.fluffy.pet.rms.resourcemanagement.transformer.PetTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    private final PetTransformer petTransformer;

    private final UserContext userContext;

    @Autowired
    public PetServiceImpl(PetRepository petRepository, PetTransformer petTransformer, UserContext userContext) {
        this.petRepository = petRepository;
        this.petTransformer = petTransformer;
        this.userContext = userContext;
    }

    @Override
    public PetResponse getPetById(String petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.PET_NOT_FOUND))
        );
        return petTransformer.convertModelToResponse(pet);
    }

    @Override
    public PetResponse createPet(PetRequest petRequest) {
        Pet pet = petTransformer.convertRequestToModel(petRequest, userContext.getUserId());
        pet.setStatus(Status.ACTIVE);
        try {
            Pet createdPet = petRepository.save(pet);
            return petTransformer.convertModelToResponse(createdPet);
        } catch (DuplicateKeyException duplicateKeyException) {
            throw new RestException(HttpStatus.BAD_REQUEST, ErrorResponse.from(ErrorCode.PET_ALREADY_EXISTS));
        }
    }

    @Override
    public PetResponse updatePet(PetRequest petRequest, String petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.PET_NOT_FOUND))
        );
        petTransformer.updatePet(pet, petRequest);
        Pet updatedPet = petRepository.save(pet);
        return petTransformer.convertModelToResponse(updatedPet);
    }

    @Override
    public void deletePet(String petId) {
        petRepository.deleteById(petId);
    }

    @Override
    public List<PetResponse> getOwnedPetsForCurrentUser() {
        List<Pet> petsByOwnerId = petRepository.findPetByOwnerId(userContext.getUserId());
        return StreamUtils.emptyIfNull(petsByOwnerId).map(petTransformer::convertModelToResponse).toList();
    }
}
