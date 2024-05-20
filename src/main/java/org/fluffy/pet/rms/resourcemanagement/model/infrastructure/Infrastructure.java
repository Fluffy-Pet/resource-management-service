package org.fluffy.pet.rms.resourcemanagement.model.infrastructure;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.model.BaseEntity;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@DynamoDBTable(tableName = DynamoConstants.INFRASTRUCTURE_TABLE)
public class Infrastructure extends BaseEntity {
    @DynamoDBAttribute(attributeName = DynamoConstants.INFRASTRUCTURE_NAME)
    private String name;

    @DynamoDBAttribute(attributeName = DynamoConstants.INFRASTRUCTURE_DESCRIPTION)
    private String description;

    @DynamoDBAttribute(attributeName = DynamoConstants.INFRASTRUCTURE_SERVICE)
    private List<Service> services;

    @DynamoDBAttribute(attributeName = DynamoConstants.INFRASTRUCTURE_TYPE)
    private String type;

    @DynamoDBAttribute(attributeName = DynamoConstants.INFRASTRUCTURE_SUB_TYPE)
    private String subType;
}
