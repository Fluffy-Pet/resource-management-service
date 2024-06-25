package org.fluffy.pet.rms.resourcemanagement.helper;

import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.model.animal.Owner;
import org.fluffy.pet.rms.resourcemanagement.model.animal.Pet;
import org.fluffy.pet.rms.resourcemanagement.util.Result;

public interface PetHelper {
    Result<Pet, ErrorCode> findPetById(String petId);

    Result<Owner, ErrorCode> findPetOwner(String petId);
}
