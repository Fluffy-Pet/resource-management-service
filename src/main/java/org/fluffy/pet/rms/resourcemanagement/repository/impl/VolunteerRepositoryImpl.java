package org.fluffy.pet.rms.resourcemanagement.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Volunteer;
import org.fluffy.pet.rms.resourcemanagement.repository.VolunteerRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.CommonRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.impl.CommonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VolunteerRepositoryImpl implements VolunteerRepository {
    private final MongoTemplate mongoTemplate;

    private final ObjectMapper objectMapper;

    @Autowired
    public VolunteerRepositoryImpl(MongoTemplate mongoTemplate, ObjectMapper objectMapper) {
        this.mongoTemplate = mongoTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public CommonRepository<Volunteer, String> getCommonRepository() {
        return new CommonRepositoryImpl<>(
                mongoTemplate,
                objectMapper,
                Volunteer.class
        );
    }
}
