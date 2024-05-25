package org.fluffy.pet.rms.resourcemanagement.repository.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.fluffy.pet.rms.resourcemanagement.model.infrastructure.Infrastructure;
import org.fluffy.pet.rms.resourcemanagement.repository.InfrastructureRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.CommonRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.impl.CommonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InfrastructureRepositoryImpl implements InfrastructureRepository {
    private final AmazonDynamoDB amazonDynamoDB;

    @Autowired
    public InfrastructureRepositoryImpl(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }

    @Override
    public CommonRepository<Infrastructure, String> getCommonRepository() {
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        return new CommonRepositoryImpl<>(
                dynamoDBMapper,
                amazonDynamoDB,
                Infrastructure.class
        );
    }
}
