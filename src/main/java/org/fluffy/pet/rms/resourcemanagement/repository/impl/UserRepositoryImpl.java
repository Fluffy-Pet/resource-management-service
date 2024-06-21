package org.fluffy.pet.rms.resourcemanagement.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.model.User;
import org.fluffy.pet.rms.resourcemanagement.model.common.Email;
import org.fluffy.pet.rms.resourcemanagement.model.common.Mobile;
import org.fluffy.pet.rms.resourcemanagement.repository.UserRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.CommonRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.impl.CommonRepositoryImpl;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final MongoTemplate mongoTemplate;

    private final ObjectMapper objectMapper;

    public UserRepositoryImpl(MongoTemplate mongoTemplate, ObjectMapper objectMapper) {
        this.mongoTemplate = mongoTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public CommonRepository<User, String> getCommonRepository() {
        return new CommonRepositoryImpl<>(
                mongoTemplate,
                objectMapper,
                User.class
        );
    }

    @Override
    public Optional<User> findUserByEmail(Email email) {
        Query query = new Query(Criteria.where(MongoConstants.STATUS).is(Status.ACTIVE).and(MongoConstants.EMAIL).is(email));
        return Optional.ofNullable(mongoTemplate.findOne(query, User.class));
    }

    @Override
    public Optional<User> findUserByMobile(Mobile mobile) {
        Query query = new Query(Criteria.where(MongoConstants.STATUS).is(Status.ACTIVE).and(MongoConstants.MOBILE).is(mobile));
        return Optional.ofNullable(mongoTemplate.findOne(query, User.class));
    }
}
