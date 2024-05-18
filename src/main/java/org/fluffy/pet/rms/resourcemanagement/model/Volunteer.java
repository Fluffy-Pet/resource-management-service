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
@Document(MongoConstants.VOLUNTEER_TABLE)
public class Volunteer extends User {
    @Field(MongoConstants.AVAILABILITY)
    private String availability; // e.g., weekends, weekdays, etc.

    @Field(MongoConstants.SKILLS)
    private String skills; // e.g., first aid, animal care, etc.

    @Field(MongoConstants.AREA_OF_OPERATION)
    private String areaOfOperation; // e.g., city or region
}
