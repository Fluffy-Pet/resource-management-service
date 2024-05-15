package org.fluffy.pet.rms.resourcemanagement.repository.common.impl;

import com.mongodb.client.result.UpdateResult;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.repository.common.CommonRepository;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommonRepositoryImpl<T, ID> implements CommonRepository<T, ID> {
    private final MongoTemplate mongoTemplate;

    private final Class<T> entityClass;

    public CommonRepositoryImpl(MongoTemplate mongoTemplate, Class<T> entityClass) {
        this.mongoTemplate = mongoTemplate;
        this.entityClass = entityClass;
    }

    @Override
    public T save(T t) {
        return mongoTemplate.save(t);
    }

    @Override
    public Optional<T> findById(ID id) {
        Query query = new Query(Criteria.where(MongoConstants.STATUS).is(Status.ACTIVE).and(MongoConstants.ID).is(id));
        return Optional.ofNullable(mongoTemplate.findOne(query, entityClass));
    }

    @Override
    public boolean deleteById(ID id) {
        Update update = Update.update(MongoConstants.STATUS, Status.INACTIVE);
        Query query = new Query(Criteria.where(MongoConstants.ID).is(id).and(MongoConstants.STATUS).is(Status.ACTIVE));
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, entityClass);
        return updateResult.getModifiedCount() > 0;
    }

    @Override
    public List<T> findAllByIds(Iterable<ID> ids) {
        ArrayList<ID> organizationIds = new ArrayList<>();
        ids.forEach(organizationIds::add);
        Query query = new Query(
                Criteria.where(MongoConstants.STATUS).is(Status.ACTIVE).and(MongoConstants.ID).in(organizationIds)
        );
        return mongoTemplate.find(query, entityClass);
    }
}
