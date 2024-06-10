package org.fluffy.pet.rms.resourcemanagement.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserEmail;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserMobile;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@SuperBuilder
@Document(MongoConstants.USER_TABLE)
public class User extends BaseEntity {
    @Field(MongoConstants.MOBILE)
    private UserMobile mobile;

    @Field(MongoConstants.EMAIL)
    private UserEmail email;

    @Field(MongoConstants.PASSWORD)
    private String password;
}
