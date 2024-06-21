package org.fluffy.pet.rms.resourcemanagement.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final ObjectMapper objectMapper;

    @Autowired
    public AdminRepositoryImpl(MongoTemplate mongoTemplate, ObjectMapper objectMapper) {
        this.mongoTemplate = mongoTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public CommonRepository<Admin, String> getCommonRepository() {
        return new CommonRepositoryImpl<>(
                mongoTemplate,
                objectMapper,
                Admin.class
        );
    }
}
