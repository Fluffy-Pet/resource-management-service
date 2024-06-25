package org.fluffy.pet.rms.resourcemanagement.model.clinic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.model.BaseEntity;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;
import org.fluffy.pet.rms.resourcemanagement.model.common.OperatingHours;
import org.fluffy.pet.rms.resourcemanagement.model.common.Owner;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Document(MongoConstants.CLINIC_TABLE)
public class Clinic extends BaseEntity {
    @Field(MongoConstants.CLINIC_NAME)
    private String clinicName;

    @Field(MongoConstants.CLINIC_DESCRIPTION)
    private String description;

    @Field(MongoConstants.ADDRESS)
    private Address address;

    @Field(MongoConstants.PROFILE_IMAGE_FILE_NAME)
    private String profileImageFileName;

    @Field(MongoConstants.OWNER)
    private Owner owner;

    @Field(MongoConstants.OPERATING_HOURS)
    private OperatingHours operatingHours;
}
