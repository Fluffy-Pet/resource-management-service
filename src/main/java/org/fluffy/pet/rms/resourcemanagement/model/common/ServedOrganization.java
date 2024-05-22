package org.fluffy.pet.rms.resourcemanagement.model.common;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
public class ServedOrganization {
    @DynamoDBAttribute(attributeName = DynamoConstants.ORGANIZATION_NAME)
    private String organizationName;

    @DynamoDBAttribute(attributeName = DynamoConstants.ROLE)
    private String role;

    @DynamoDBAttribute(attributeName = DynamoConstants.START_DATE)
    private LocalDate startDate;

    @DynamoDBAttribute(attributeName = DynamoConstants.END_DATE)
    private LocalDate endDate;
}
