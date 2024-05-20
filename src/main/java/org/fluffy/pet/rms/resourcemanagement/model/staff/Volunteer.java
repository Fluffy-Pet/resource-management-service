package org.fluffy.pet.rms.resourcemanagement.model.staff;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.enums.AvailabilityType;
import org.fluffy.pet.rms.resourcemanagement.enums.SkillType;
import org.fluffy.pet.rms.resourcemanagement.model.BaseEntity;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;
import org.fluffy.pet.rms.resourcemanagement.model.common.Document;
import org.fluffy.pet.rms.resourcemanagement.model.common.ServedOrganization;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@DynamoDBTable(tableName = DynamoConstants.VOLUNTEER_TABLE)
public class Volunteer extends BaseEntity {
    @DynamoDBAttribute(attributeName = DynamoConstants.AVAILABILITY)
    private List<AvailabilityType> availability; // List of availability types

    @DynamoDBAttribute(attributeName = DynamoConstants.SKILLS)
    private List<SkillType> skills; // List of skills

    @DynamoDBAttribute(attributeName = DynamoConstants.DOCUMENT)
    private List<Document> documents;

    @DynamoDBAttribute(attributeName = DynamoConstants.ADDRESS)
    private Address address;

    @DynamoDBAttribute(attributeName = DynamoConstants.SERVED_ORGANIZATIONS)
    private List<ServedOrganization> servedOrganizations; // List of previously served organizations
}
