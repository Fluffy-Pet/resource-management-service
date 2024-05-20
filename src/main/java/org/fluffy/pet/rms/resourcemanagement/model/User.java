package org.fluffy.pet.rms.resourcemanagement.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.model.common.Email;
import org.fluffy.pet.rms.resourcemanagement.model.common.Mobile;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;

@Getter
@Setter
@SuperBuilder
@DynamoDBTable(tableName = DynamoConstants.USER_TABLE)
public class User extends BaseEntity {
    @DynamoDBAttribute(attributeName = DynamoConstants.MOBILE)
    private Mobile mobile;

    @DynamoDBAttribute(attributeName = DynamoConstants.EMAIL)
    private Email email;

    @DynamoDBAttribute(attributeName = DynamoConstants.PASSWORD)
    private String password;
}
