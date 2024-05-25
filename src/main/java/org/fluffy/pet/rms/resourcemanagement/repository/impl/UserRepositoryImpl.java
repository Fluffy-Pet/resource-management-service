package org.fluffy.pet.rms.resourcemanagement.repository.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.model.User;
import org.fluffy.pet.rms.resourcemanagement.repository.UserRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.CommonRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.impl.CommonRepositoryImpl;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<User> findUserByEmail(String email) {
        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withIndexName(DynamoConstants.EMAIL_INDEX)
                .withConsistentRead(false);
        queryExpression = queryExpression.addExpressionAttributeValuesEntry(
                DynamoConstants.EMAIL,
                new AttributeValue().withS(email)
        );
        queryExpression = queryExpression.addExpressionAttributeValuesEntry(
                DynamoConstants.STATUS,
                new AttributeValue().withS(Status.ACTIVE.name())
        );

        List<User> userList = dynamoDBMapper.query(User.class, queryExpression);
        return userList.isEmpty() ? Optional.empty() : Optional.of(userList.getFirst());
    }

    @Override
    public Optional<User> findUserByMobile(String mobile) {
        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withIndexName(DynamoConstants.MOBILE_INDEX)
                .withConsistentRead(false);
        queryExpression = queryExpression.addExpressionAttributeValuesEntry(
                DynamoConstants.MOBILE,
                new AttributeValue().withS(mobile)
        );
        queryExpression = queryExpression.addExpressionAttributeValuesEntry(
                DynamoConstants.STATUS,
                new AttributeValue().withS(Status.ACTIVE.name())
        );

        List<User> userList = dynamoDBMapper.query(User.class, queryExpression);
        return userList.isEmpty() ? Optional.empty() : Optional.of(userList.getFirst());
    }
}
