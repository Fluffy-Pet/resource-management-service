package org.fluffy.pet.rms.resourcemanagement.repository;

import org.fluffy.pet.rms.resourcemanagement.model.service.FluffyPetService;

import java.util.List;

public interface FluffyPetServiceRepository extends BaseRepository<FluffyPetService, String> {
    List<FluffyPetService> findByProviderId(String providerId);
}
