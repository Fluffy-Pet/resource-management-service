package org.fluffy.pet.rms.resourcemanagement.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@SuperBuilder
@Document(MongoConstants.DOCTOR_TABLE)
public class Doctor extends User {
    @Field(MongoConstants.SPECIALIZATION)
    private String specialization;

    @Field(MongoConstants.EXPERIENCE)
    private Integer experience; // in years

    @Field(MongoConstants.CLINIC_ADDRESS)
    private String clinicAddress;

    @Field(MongoConstants.LICENSE_NUMBER)
    private String licenseNumber;
}
