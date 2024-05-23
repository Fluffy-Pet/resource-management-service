package org.fluffy.pet.rms.resourcemanagement.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Volunteer;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;

import java.util.List;
import java.util.stream.Collectors;

public class VolunteerRepository {

    private final DynamoDBMapper dynamoDBMapper;

    private final AmazonDynamoDB amazonDynamoDB;

    public VolunteerRepository(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB amazonDynamoDB) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.amazonDynamoDB = amazonDynamoDB;
    }

    public void save(Volunteer volunteer) {
        dynamoDBMapper.save(volunteer);
    }
    public void findById(String id) {
        dynamoDBMapper.load(Volunteer.class, id);
    }

    public List<Volunteer> findAll() {
        ScanRequest scanRequest = new ScanRequest()
                .withTableName(DynamoConstants.VOLUNTEER_TABLE);
        ScanResult result = amazonDynamoDB.scan(scanRequest);

        return result.getItems().stream()
                .map(item -> dynamoDBMapper.marshallIntoObject(Volunteer.class, item))
                .collect(Collectors.toList());
    }
}
