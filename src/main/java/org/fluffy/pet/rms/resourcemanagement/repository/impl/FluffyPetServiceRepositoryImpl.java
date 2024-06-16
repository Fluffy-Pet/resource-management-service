package org.fluffy.pet.rms.resourcemanagement.repository.impl;

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

    @Override
    public List<FluffyPetService> findByProviderId(String providerId) {
        Query query = new Query(
                Criteria.where(MongoConstants.STATUS).is(Status.ACTIVE)
                        .and(String.format("%s.%s", MongoConstants.PROVIDER, MongoConstants.PROVIDER_ID))
                        .is(providerId)
        );
        return mongoTemplate.find(query, FluffyPetService.class);
    }
}
