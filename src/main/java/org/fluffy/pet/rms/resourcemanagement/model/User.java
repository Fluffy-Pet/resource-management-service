package org.fluffy.pet.rms.resourcemanagement.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.model.common.Email;
import org.fluffy.pet.rms.resourcemanagement.model.common.Mobile;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@SuperBuilder
@Document(MongoConstants.USER_TABLE)
public class User extends BaseEntity {
    @Field(MongoConstants.MOBILE)
    private Mobile mobile;

    @Field(MongoConstants.EMAIL)
    private Email email;

    private String password;
}
