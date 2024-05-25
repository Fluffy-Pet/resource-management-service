package org.fluffy.pet.rms.resourcemanagement.model.common;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.enums.DocumentType;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;

@Getter
@Setter
@SuperBuilder
@DynamoDBDocument
public class Document {
    @DynamoDBAttribute(attributeName = DynamoConstants.TYPE)
    private DocumentType type;

    @DynamoDBAttribute(attributeName = DynamoConstants.ID_NUMBER)
    private String idNumber;

    @DynamoDBAttribute(attributeName = DynamoConstants.URL)
    private String url;
}
