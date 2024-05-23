package org.fluffy.pet.rms.resourcemanagement.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Doctor;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;

import java.util.List;
import java.util.stream.Collectors;

public class DoctorRepository {

    private final DynamoDBMapper dynamoDBMapper;
    private final AmazonDynamoDB amazonDynamoDB;

    public DoctorRepository(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB amazonDynamoDB) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.amazonDynamoDB = amazonDynamoDB;
    }

    public void save(Doctor doctor) {
        dynamoDBMapper.save(doctor);
    }

    public Doctor findById(String id) {
        return dynamoDBMapper.load(Doctor.class, id);
    }

    public List<Doctor> findAll() {
        ScanRequest scanRequest = new ScanRequest()
                .withTableName(DynamoConstants.DOCTOR_TABLE);
        ScanResult result = amazonDynamoDB.scan(scanRequest);

        return result.getItems().stream()
                .map(item -> dynamoDBMapper.marshallIntoObject(Doctor.class, item))
                .collect(Collectors.toList());
    }
}
