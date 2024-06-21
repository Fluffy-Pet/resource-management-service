package org.fluffy.pet.rms.resourcemanagement.model.staff;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.model.BaseEntity;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Document(MongoConstants.ADMIN_TABLE)
public class Admin extends BaseEntity {
    @Field(MongoConstants.FIRST_NAME)
    private String firstName;

    @Field(MongoConstants.LAST_NAME)
    private String lastName;

    @Field(MongoConstants.PROFILE_IMAGE_FILE_NAME)
    private String profileImageFileName;
}
