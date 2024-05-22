package org.fluffy.pet.rms.resourcemanagement.model.staff;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;
import org.fluffy.pet.rms.resourcemanagement.model.BaseEntity;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;
import org.fluffy.pet.rms.resourcemanagement.model.common.Document;
import org.fluffy.pet.rms.resourcemanagement.model.common.ServedOrganization;
import org.fluffy.pet.rms.resourcemanagement.model.infrastructure.Clinic;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@DynamoDBTable(tableName = DynamoConstants.DOCTOR_TABLE)
public class Doctor extends BaseEntity {
    @DynamoDBAttribute(attributeName = DynamoConstants.SPECIALIZATION)
    private List<PetType> specialization;

    @DynamoDBAttribute(attributeName = DynamoConstants.EXPERIENCE)
    private Double experience;

    @DynamoDBAttribute(attributeName = DynamoConstants.DOCUMENT)
    private List<Document> documents;

    @DynamoDBAttribute(attributeName = DynamoConstants.ASSOCIATED_CLINICS)
    private List<Clinic> associatedClinics;

    @DynamoDBAttribute(attributeName = DynamoConstants.ADDRESS)
    private Address address;

    @DynamoDBAttribute(attributeName = DynamoConstants.SERVED_ORGANIZATIONS)
    private List<ServedOrganization> servedOrganizations;
}
