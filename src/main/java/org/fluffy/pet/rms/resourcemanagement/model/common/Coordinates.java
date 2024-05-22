package org.fluffy.pet.rms.resourcemanagement.model.common;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;

@Getter
@Setter
@SuperBuilder
public class Coordinates {
    @DynamoDBAttribute(attributeName = DynamoConstants.LATITUDE)
    private double latitude;

    @DynamoDBAttribute(attributeName = DynamoConstants.LONGITUDE)
    private double longitude;
}
