package org.fluffy.pet.rms.resourcemanagement.repository.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.fluffy.pet.rms.resourcemanagement.model.User;
import org.fluffy.pet.rms.resourcemanagement.repository.UserRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.CommonRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.impl.CommonRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final AmazonDynamoDB amazonDynamoDB;

    private final DynamoDBMapper dynamoDBMapper;

    public UserRepositoryImpl(AmazonDynamoDB amazonDynamoDB, DynamoDBMapper dynamoDBMapper) {
        this.amazonDynamoDB = amazonDynamoDB;
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public CommonRepository<User, String> getCommonRepository() {
        return new CommonRepositoryImpl<>(
                dynamoDBMapper,
                amazonDynamoDB,
                User.class
        );
    }
}
