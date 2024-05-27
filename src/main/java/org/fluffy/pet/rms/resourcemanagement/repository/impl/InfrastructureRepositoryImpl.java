package org.fluffy.pet.rms.resourcemanagement.repository.impl;

import org.fluffy.pet.rms.resourcemanagement.model.infrastructure.Infrastructure;
import org.fluffy.pet.rms.resourcemanagement.repository.InfrastructureRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.CommonRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.impl.CommonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InfrastructureRepositoryImpl implements InfrastructureRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public InfrastructureRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public CommonRepository<Infrastructure, String> getCommonRepository() {
        return new CommonRepositoryImpl<>(
                mongoTemplate,
                Infrastructure.class
        );
    }
}
