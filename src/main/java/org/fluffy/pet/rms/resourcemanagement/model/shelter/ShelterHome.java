package org.fluffy.pet.rms.resourcemanagement.model.shelter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.model.BaseEntity;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;
import org.fluffy.pet.rms.resourcemanagement.model.common.Owner;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Document(MongoConstants.SHELTER_HOME_TABLE)
public class ShelterHome extends BaseEntity {
    @Field(MongoConstants.SHELTER_HOME_NAME)
    private String name;

    @Field(MongoConstants.SHELTER_HOME_DESCRIPTION)
    private String description;

    @Field(MongoConstants.PROFILE_IMAGE_FILE_NAME)
    private String profileImageFileName;

    @Field(MongoConstants.ADDRESS)
    private Address address;

    @Field(MongoConstants.OWNER)
    private Owner owner;
}