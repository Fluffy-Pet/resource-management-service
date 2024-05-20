package org.fluffy.pet.rms.resourcemanagement.model.staff;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;
import org.fluffy.pet.rms.resourcemanagement.model.BaseEntity;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;
import org.fluffy.pet.rms.resourcemanagement.model.common.ServedOrganization;
import org.fluffy.pet.rms.resourcemanagement.model.infrastructure.Clinic;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@Document(MongoConstants.DOCTOR_TABLE)
public class Doctor extends BaseEntity {
    @Field(MongoConstants.SPECIALIZATION)
    private List<PetType>  specialization;

    @Field(MongoConstants.EXPERIENCE)
    private Double experience; // in years

    @Field(MongoConstants.DOCUMENT)
    private List<Document> documents;

    @Field(MongoConstants.ASSOCIATED_CLINICS)
    private List<Clinic> associatedClinics;

    @Field(MongoConstants.ADDRESS)
    private Address address;

    @Field(MongoConstants.SERVED_ORGANIZATIONS)
    private List<ServedOrganization> servedOrganizations;
}
