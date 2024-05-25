package org.fluffy.pet.rms.resourcemanagement.repository.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.fluffy.pet.rms.resourcemanagement.model.infrastructure.Clinic;
import org.fluffy.pet.rms.resourcemanagement.repository.ClinicRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.CommonRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.common.impl.CommonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClinicRepositoryImpl implements ClinicRepository {
    private final AmazonDynamoDB amazonDynamoDB;

    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public ClinicRepositoryImpl(AmazonDynamoDB amazonDynamoDB, DynamoDBMapper dynamoDBMapper) {
        this.amazonDynamoDB = amazonDynamoDB;
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public CommonRepository<Clinic, String> getCommonRepository() {
        return new CommonRepositoryImpl<>(
                dynamoDBMapper,
                amazonDynamoDB,
                Clinic.class
        );
    }
}
