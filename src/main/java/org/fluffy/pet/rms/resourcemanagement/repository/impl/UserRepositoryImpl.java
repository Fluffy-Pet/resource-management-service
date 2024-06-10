package org.fluffy.pet.rms.resourcemanagement.repository.impl;

import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.model.User;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserEmail;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserMobile;
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

    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public CommonRepository<User, String> getCommonRepository() {
        return new CommonRepositoryImpl<>(
                mongoTemplate,
                User.class
        );
    }

    @Override
    public Optional<User> findUserByEmail(UserEmail userEmail) {
        Query query = new Query(Criteria.where(MongoConstants.STATUS).is(Status.ACTIVE).and(MongoConstants.EMAIL).is(userEmail));
        return Optional.ofNullable(mongoTemplate.findOne(query, User.class));
    }

    @Override
    public Optional<User> findUserByMobile(UserMobile userMobile) {
        Query query = new Query(Criteria.where(MongoConstants.STATUS).is(Status.ACTIVE).and(MongoConstants.MOBILE).is(userMobile));
        return Optional.ofNullable(mongoTemplate.findOne(query, User.class));
    }
}
