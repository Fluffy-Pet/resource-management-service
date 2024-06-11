package org.fluffy.pet.rms.resourcemanagement.repository;

import org.fluffy.pet.rms.resourcemanagement.model.animal.Pet;

import java.util.List;

public interface PetRepository extends BaseRepository<Pet, String> {
    List<Pet> findPetByOwnerId(String ownerId);
}
