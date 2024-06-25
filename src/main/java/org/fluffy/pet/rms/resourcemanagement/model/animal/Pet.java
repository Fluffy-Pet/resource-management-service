package org.fluffy.pet.rms.resourcemanagement.model.animal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;
import org.fluffy.pet.rms.resourcemanagement.model.BaseEntity;
import org.fluffy.pet.rms.resourcemanagement.model.common.Owner;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Document(MongoConstants.PET_TABLE)
public class Pet extends BaseEntity {
    @Field(MongoConstants.NAME)
    private String name;

    @Field(MongoConstants.PET_TYPE)
    private PetType petType;

    @Field(MongoConstants.DATE_OF_BIRTH)
    private LocalDate dateOfBirth;

    @Field(MongoConstants.PROFILE_IMAGE_FILE_NAME)
    private String profileImageFileName;

    @Field(MongoConstants.OWNER)
    private Owner owner;
}
