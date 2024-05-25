package org.fluffy.pet.rms.resourcemanagement.model.infrastructure;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.model.BaseEntity;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;

@Getter
@Setter
@SuperBuilder
@DynamoDBTable(tableName = DynamoConstants.CLINIC_TABLE)
public class Clinic extends BaseEntity {
    @DynamoDBAttribute(attributeName = DynamoConstants.CLINIC_NAME)
    private String clinicName;

    @DynamoDBAttribute(attributeName = DynamoConstants.ADDRESS)
    private Address address;

    @DynamoDBAttribute(attributeName = DynamoConstants.PHONE_NUMBER)
    private String phoneNumber;

    @DynamoDBAttribute(attributeName = DynamoConstants.SERVICES_OFFERED)
    private String servicesOffered;

    @DynamoDBAttribute(attributeName = DynamoConstants.OPERATING_HOURS)
    private String operatingHours;
}
