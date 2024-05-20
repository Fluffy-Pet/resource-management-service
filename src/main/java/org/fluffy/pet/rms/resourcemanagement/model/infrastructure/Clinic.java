package org.fluffy.pet.rms.resourcemanagement.model.infrastructure;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;

import java.time.Duration;

@Getter
@Setter
@SuperBuilder
@DynamoDBTable(tableName = "VeterinaryClinic")
public class Clinic {
    @DynamoDBAttribute(attributeName = DynamoConstants.CLINIC_NAME)
    private String clinicName;

    @DynamoDBAttribute(attributeName = DynamoConstants.ADDRESS)
    private Address address;

    @DynamoDBAttribute(attributeName = DynamoConstants.PHONE_NUMBER)
    private String phoneNumber;

    @DynamoDBAttribute(attributeName = DynamoConstants.SERVICES_OFFERED)
    private String servicesOffered; // e.g., surgery, vaccination, etc.

    @DynamoDBAttribute(attributeName = DynamoConstants.OPERATING_HOURS)
    private String operatingHours; // e.g., 9 AM - 5 PM
}
