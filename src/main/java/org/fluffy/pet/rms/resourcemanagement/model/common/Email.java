package org.fluffy.pet.rms.resourcemanagement.model.common;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Email {
    @DynamoDBAttribute(attributeName = DynamoConstants.EMAIL_ID)
    private String emailId;
}
