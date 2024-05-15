package org.fluffy.pet.rms.resourcemanagement.repository.impl;

import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.model.User;
import org.fluffy.pet.rms.resourcemanagement.model.common.Email;
import org.fluffy.pet.rms.resourcemanagement.model.common.Mobile;
import org.fluffy.pet.rms.resourcemanagement.repository.UserRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.CommonRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.impl.CommonRepositoryImpl;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final CommonRepository<User, String> commonRepository;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserRepositoryImpl(MongoTemplate mongoTemplate, MongoTemplate mongoTemplate1) {
        this.commonRepository = new CommonRepositoryImpl<>(mongoTemplate, User.class);
        this.mongoTemplate = mongoTemplate1;
    }

    @Override
    public CommonRepository<User, String> getCommonRepository() {
        return commonRepository;
    }

    @Override
    public Optional<User> findUserByMobile(Mobile mobile) {
        Query query = new Query(
                Criteria.where(MongoConstants.MOBILE)
                        .is(mobile)
                        .and(MongoConstants.STATUS)
                        .is(Status.ACTIVE)

        );
        return Optional.ofNullable(mongoTemplate.findOne(query, User.class));
    }

    @Override
    public Optional<User> findUserByEmail(Email email) {
        Query query = new Query(
                Criteria.where(MongoConstants.EMAIL)
                        .is(email)
                        .and(MongoConstants.STATUS)
                        .is(Status.ACTIVE)

        );
        return Optional.ofNullable(mongoTemplate.findOne(query, User.class));
    }
}
