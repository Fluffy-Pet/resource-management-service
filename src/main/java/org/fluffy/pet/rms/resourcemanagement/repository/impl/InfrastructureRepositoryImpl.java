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
    private final CommonRepository<Infrastructure, String> commonRepository;

    @Autowired
    public InfrastructureRepositoryImpl(MongoTemplate mongoTemplate) {
        this.commonRepository = new CommonRepositoryImpl<>(mongoTemplate, Infrastructure.class);
    }

    @Override
    public CommonRepository<Infrastructure, String> getCommonRepository() {
        return commonRepository;
    }
}
