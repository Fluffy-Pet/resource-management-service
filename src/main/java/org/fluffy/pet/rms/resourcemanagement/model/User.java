package org.fluffy.pet.rms.resourcemanagement.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.annotations.DynamoDBTableConfig;
import org.fluffy.pet.rms.resourcemanagement.annotations.GlobalSecondaryIndexAnnotation;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;

@Getter
@Setter
@SuperBuilder
@DynamoDBTable(tableName = DynamoConstants.USER_TABLE)
@DynamoDBTableConfig(
        tableName = DynamoConstants.USER_TABLE,
        globalSecondaryIndexes = {
                @GlobalSecondaryIndexAnnotation(indexName = DynamoConstants.EMAIL_INDEX, hashKey = DynamoConstants.EMAIL),
                @GlobalSecondaryIndexAnnotation(indexName = DynamoConstants.MOBILE_INDEX, hashKey = DynamoConstants.MOBILE),
                @GlobalSecondaryIndexAnnotation(indexName = DynamoConstants.STATUS_INDEX, hashKey = DynamoConstants.STATUS)
        }
)
public class User extends BaseEntity {
    @DynamoDBAttribute(attributeName = DynamoConstants.MOBILE)
    private String mobile;

    @DynamoDBAttribute(attributeName = DynamoConstants.EMAIL_ID)
    private String emailId;

    @DynamoDBAttribute(attributeName = DynamoConstants.PASSWORD)
    private String password;
}
