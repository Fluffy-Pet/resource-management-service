package org.fluffy.pet.rms.resourcemanagement.model.staff;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.enums.AvailabilityType;
import org.fluffy.pet.rms.resourcemanagement.enums.SkillType;
import org.fluffy.pet.rms.resourcemanagement.model.User;
import org.fluffy.pet.rms.resourcemanagement.model.common.Information;
import org.fluffy.pet.rms.resourcemanagement.model.common.ServedOrganization;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@Document(MongoConstants.VOLUNTEER_TABLE)
public class Volunteer extends User {
    @Field(MongoConstants.AVAILABILITY)
    private List<AvailabilityType> availability; // List of availability types

    @Field(MongoConstants.SKILLS)
    private List<SkillType> skills; // List of skills


    @Field(MongoConstants.INFORMATION)
    private Information information; // Information details

    @Field(MongoConstants.SERVED_ORGANIZATIONS)
    private List<ServedOrganization> servedOrganizations; // List of previously served organizations
}
