package org.fluffy.pet.rms.resourcemanagement.model.client;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.model.BaseEntity;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@SuperBuilder
@Document(MongoConstants.CLIENT_TABLE)
public class Client extends BaseEntity {
    @Field(MongoConstants.FIRST_NAME)
    private String firstName;

    @Field(MongoConstants.LAST_NAME)
    private String lastName;

    @Field(MongoConstants.PROFILE_URL)
    private String profileUrl;
}
