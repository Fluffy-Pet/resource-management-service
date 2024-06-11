package org.fluffy.pet.rms.resourcemanagement.repository.impl;

import org.fluffy.pet.rms.resourcemanagement.model.staff.Admin;
import org.fluffy.pet.rms.resourcemanagement.repository.AdminRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.CommonRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.impl.CommonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepositoryImpl implements AdminRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AdminRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public CommonRepository<Admin, String> getCommonRepository() {
        return new CommonRepositoryImpl<>(
                mongoTemplate,
                Admin.class
        );
    }
}
