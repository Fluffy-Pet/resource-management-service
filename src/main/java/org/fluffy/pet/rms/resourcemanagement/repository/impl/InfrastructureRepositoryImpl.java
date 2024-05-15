package org.fluffy.pet.rms.resourcemanagement.repository.impl;

import org.fluffy.pet.rms.resourcemanagement.model.infrastructure.Infrastructure;
import org.fluffy.pet.rms.resourcemanagement.repository.InfrastructureRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InfrastructureRepositoryImpl implements InfrastructureRepository {
    private final CommonRepository<Infrastructure, String> commonRepository;

    @Autowired
    public InfrastructureRepositoryImpl(CommonRepository<Infrastructure, String> commonRepository) {
        this.commonRepository = commonRepository;
    }

    @Override
    public CommonRepository<Infrastructure, String> getCommonRepository() {
        return commonRepository;
    }
}
