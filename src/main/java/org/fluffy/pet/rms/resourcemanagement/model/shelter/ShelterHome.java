package org.fluffy.pet.rms.resourcemanagement.model.shelter;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;
import org.fluffy.pet.rms.resourcemanagement.model.BaseEntity;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;
import org.fluffy.pet.rms.resourcemanagement.model.common.ContactInformation;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@Document(MongoConstants.SHELTER_HOME_TABLE)
public class ShelterHome extends BaseEntity {

    @Field(MongoConstants.SHELTER_HOME_NAME)
    private String name;

    @Field(MongoConstants.SHELTER_HOME_DESCRIPTION)
    private String description;

    @Field(MongoConstants.CAPACITY)
    private int capacity;

    @Field(MongoConstants.CONTACT_INFORMATION)
    private ContactInformation contactInformation;

    @Field(MongoConstants.ADDRESS)
    private Address address;

    @Field(MongoConstants.SHELTER_HOME_WEBSITE)
    private String website;


    @Field(MongoConstants.PETS_ACCEPTED)
    private List<PetType> acceptedPetTypes;

    @Field(MongoConstants.ADOPTION_INFORMATION)
    private String adoptionInformation;
}