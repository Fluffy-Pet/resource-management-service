package org.fluffy.pet.rms.resourcemanagement.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.fluffy.pet.rms.resourcemanagement.annotations.Helper;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.helper.PetHelper;
import org.fluffy.pet.rms.resourcemanagement.model.animal.Owner;
import org.fluffy.pet.rms.resourcemanagement.model.animal.Pet;
import org.fluffy.pet.rms.resourcemanagement.repository.PetRepository;
import org.fluffy.pet.rms.resourcemanagement.util.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Helper
@Slf4j
public class PetHelperImpl implements PetHelper {
    private final PetRepository petRepository;

    @Autowired
    public PetHelperImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Result<Pet, ErrorCode> findPetById(String petId) {
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isEmpty()) {
            return Result.error(ErrorCode.PET_NOT_FOUND);
        }
        return Result.success(optionalPet.get());
    }

    @Override
    public Result<Owner, ErrorCode> findPetOwner(String petId) {
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isEmpty()) {
            return Result.error(ErrorCode.PET_NOT_FOUND);
        }
        return Result.success(optionalPet.get().getOwner());
    }
}
