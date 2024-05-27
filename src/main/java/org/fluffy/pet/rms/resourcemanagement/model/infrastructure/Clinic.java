package org.fluffy.pet.rms.resourcemanagement.model.infrastructure;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.model.BaseEntity;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@SuperBuilder
@Document(MongoConstants.CLINIC_TABLE)
public class Clinic extends BaseEntity {
    @Field(MongoConstants.CLINIC_NAME)
    private String clinicName;

    @Field(MongoConstants.ADDRESS)
    private Address address;

    @Field(MongoConstants.PHONE_NUMBER)
    private String phoneNumber;

    @Field(MongoConstants.SERVICES_OFFERED)
    private String servicesOffered;

    @Field(MongoConstants.OPERATING_HOURS)
    private String operatingHours;
}
