package org.fluffy.pet.rms.resourcemanagement.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import org.fluffy.pet.rms.resourcemanagement.model.infrastructure.Clinic;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;

import java.util.List;
import java.util.stream.Collectors;

public class ClinicRepository {
    private final DynamoDBMapper dynamoDBMapper;

    private final AmazonDynamoDB amazonDynamoDB;

    public ClinicRepository(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB amazonDynamoDB) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.amazonDynamoDB = amazonDynamoDB;
    }

    public void save(Clinic clinic) {
        dynamoDBMapper.save(clinic);
    }

    public void findById(String id) {
        dynamoDBMapper.load(Clinic.class, id);
    }

    public List<Clinic> findAll() {
        ScanRequest scanRequest = new ScanRequest()
                .withTableName(DynamoConstants.CLINIC_TABLE);
        ScanResult result = amazonDynamoDB.scan(scanRequest);

        return result.getItems().stream()
                .map(item -> dynamoDBMapper.marshallIntoObject(Clinic.class, item))
                .collect(Collectors.toList());
    }

}
