package org.fluffy.pet.rms.resourcemanagement.repository.impl;

import org.fluffy.pet.rms.resourcemanagement.model.service.FluffyPetService;
import org.fluffy.pet.rms.resourcemanagement.repository.FluffyPetServiceRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.CommonRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.impl.CommonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FluffyPetServiceRepositoryImpl implements FluffyPetServiceRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public FluffyPetServiceRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public CommonRepository<FluffyPetService, String> getCommonRepository() {
        return new CommonRepositoryImpl<>(mongoTemplate, FluffyPetService.class);
    }
}
