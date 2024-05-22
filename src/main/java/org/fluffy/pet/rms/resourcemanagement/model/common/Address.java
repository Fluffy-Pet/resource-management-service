package org.fluffy.pet.rms.resourcemanagement.model.common;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;

@Getter
@Setter
@SuperBuilder
public class Address {
    @DynamoDBAttribute(attributeName = DynamoConstants.STREET)
    private String street;

    @DynamoDBAttribute(attributeName = DynamoConstants.CITY)
    private String city;

    @DynamoDBAttribute(attributeName = DynamoConstants.STATE)
    private String state;

    @DynamoDBAttribute(attributeName = DynamoConstants.ZIP_CODE)
    private String zipCode;

    @DynamoDBAttribute(attributeName = DynamoConstants.COUNTRY)
    private String country;

    @DynamoDBAttribute(attributeName = DynamoConstants.COORDINATES)
    private Coordinates coordinates;
}
