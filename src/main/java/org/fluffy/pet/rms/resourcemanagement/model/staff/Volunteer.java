package org.fluffy.pet.rms.resourcemanagement.model.staff;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.enums.AvailabilityType;
import org.fluffy.pet.rms.resourcemanagement.enums.SkillType;
import org.fluffy.pet.rms.resourcemanagement.model.BaseEntity;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;
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
@Document(MongoConstants.VOLUNTEER_TABLE)
public class Volunteer extends BaseEntity {
    @Field(MongoConstants.FIRST_NAME)
    private String firstName;

    @Field(MongoConstants.LAST_NAME)
    private String lastName;

    @Field(MongoConstants.PROFILE_IMAGE_FILE_NAME)
    private String profileImageFileName;

    @Field(MongoConstants.AVAILABILITY)
    private List<AvailabilityType> availability;

    @Field(MongoConstants.SKILLS)
    private List<SkillType> skills;

    @Field(MongoConstants.DOCUMENT)
    private List<IdentityDocument> identityDocuments;

    @Field(MongoConstants.ADDRESS)
    private Address address;

    @Field(MongoConstants.SERVED_ORGANIZATIONS)
    private List<ServedOrganization> servedOrganizations;
}
