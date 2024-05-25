package org.fluffy.pet.rms.resourcemanagement.model.infrastructure;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.PetCategory;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@DynamoDBDocument
public class Service {
    @DynamoDBAttribute(attributeName = DynamoConstants.SERVICE_GROUP)
    private String serviceGroup;

    @DynamoDBAttribute(attributeName = DynamoConstants.SERVICE_SUB_GROUP)
    private String serviceSubGroup;

    @DynamoDBAttribute(attributeName = DynamoConstants.PET_CATEGORY)
    private PetCategory petCategory;
}
