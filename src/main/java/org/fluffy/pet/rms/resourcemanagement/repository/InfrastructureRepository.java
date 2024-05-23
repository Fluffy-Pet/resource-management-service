package org.fluffy.pet.rms.resourcemanagement.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import org.fluffy.pet.rms.resourcemanagement.model.infrastructure.Infrastructure;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;

import java.util.List;
import java.util.stream.Collectors;

public class InfrastructureRepository {
    private final DynamoDBMapper dynamoDBMapper;

    private final AmazonDynamoDB amazonDynamoDB;

    public InfrastructureRepository(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB amazonDynamoDB) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.amazonDynamoDB = amazonDynamoDB;
    }

    public void save(Infrastructure infrastructure) {
        dynamoDBMapper.save(infrastructure);
    }

    public void findById(String id) {
        dynamoDBMapper.load(Infrastructure.class, id);
    }

    public List<Infrastructure> findAll() {
        ScanRequest scanRequest = new ScanRequest()
                .withTableName(DynamoConstants.INFRASTRUCTURE_TABLE);
        ScanResult result = amazonDynamoDB.scan(scanRequest);

        return result.getItems().stream()
                .map(item -> dynamoDBMapper.marshallIntoObject(Infrastructure.class, item))
                .collect(Collectors.toList());
    }
}
