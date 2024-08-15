package org.fluffy.pet.rms.resourcemanagement.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fluffy.pet.rms.resourcemanagement.enums.ServiceSubType;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.model.service.FluffyPetService;
import org.fluffy.pet.rms.resourcemanagement.repository.FluffyPetServiceRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.CommonRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.impl.CommonRepositoryImpl;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class FluffyPetServiceRepositoryImpl implements FluffyPetServiceRepository {
    private final MongoTemplate mongoTemplate;

    private final ObjectMapper objectMapper;

    @Autowired
    public FluffyPetServiceRepositoryImpl(MongoTemplate mongoTemplate, ObjectMapper objectMapper) {
        this.mongoTemplate = mongoTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public CommonRepository<FluffyPetService, String> getCommonRepository() {
        return new CommonRepositoryImpl<>(mongoTemplate, objectMapper, FluffyPetService.class);
    }

    @Override
    public List<FluffyPetService> findByProviderId(String providerId) {
        Query query = new Query(
                Criteria.where(MongoConstants.STATUS).is(Status.ACTIVE)
                        .and(String.format("%s.%s", MongoConstants.PROVIDER, MongoConstants.PROVIDER_ID))
                        .is(providerId)
        );
        return mongoTemplate.find(query, FluffyPetService.class);
    }

    @Override
    public List<FluffyPetService> findAllByAllServiceSubType(Iterable<ServiceSubType> serviceSubTypes) {
        Query query = new Query(
                Criteria.where(MongoConstants.STATUS).is(Status.ACTIVE)
                        .and(MongoConstants.SERVICE_SUB_TYPE)
                        .in(StreamSupport.stream(serviceSubTypes.spliterator(), false).collect(Collectors.toList()))
        );
        return mongoTemplate.find(query, FluffyPetService.class);
    }
}
