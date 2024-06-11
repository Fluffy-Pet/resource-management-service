package org.fluffy.pet.rms.resourcemanagement.repository.impl;

import org.fluffy.pet.rms.resourcemanagement.model.shelter.ShelterHome;
import org.fluffy.pet.rms.resourcemanagement.repository.ShelterHomeRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.CommonRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.impl.CommonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShelterHomeRepositoryImpl implements ShelterHomeRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ShelterHomeRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public CommonRepository<ShelterHome, String> getCommonRepository() {
        return new CommonRepositoryImpl<>(
                mongoTemplate,
                ShelterHome.class
        );
    }
}