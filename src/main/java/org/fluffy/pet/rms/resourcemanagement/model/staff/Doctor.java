package org.fluffy.pet.rms.resourcemanagement.model.staff;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;
import org.fluffy.pet.rms.resourcemanagement.model.BaseEntity;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;
import org.fluffy.pet.rms.resourcemanagement.model.common.AssociatedClinic;
import org.fluffy.pet.rms.resourcemanagement.model.common.IdentityDocument;
import org.fluffy.pet.rms.resourcemanagement.model.common.ServedOrganization;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Document(MongoConstants.DOCTOR_TABLE)
public class Doctor extends BaseEntity {
    @Field(MongoConstants.FIRST_NAME)
    private String firstName;

    @Field(MongoConstants.LAST_NAME)
    private String lastName;

    @Field(MongoConstants.SPECIALIZATION)
    private List<PetType> specialization;

    @Field(MongoConstants.EXPERIENCE)
    private Double experience;

    @Field(MongoConstants.DOCUMENT)
    private List<IdentityDocument> identityDocuments;

    @Field(MongoConstants.ASSOCIATED_CLINICS)
    private List<AssociatedClinic> associatedClinics;

    @Field(MongoConstants.ADDRESS)
    private Address address;

    @Field(MongoConstants.SERVED_ORGANIZATIONS)
    private List<ServedOrganization> servedOrganizations;
}
