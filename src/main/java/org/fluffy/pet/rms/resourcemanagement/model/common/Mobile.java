package org.fluffy.pet.rms.resourcemanagement.model.common;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Mobile {
    @DynamoDBAttribute(attributeName = DynamoConstants.COUNTRY_CODE)
    private String countryCode;

    @DynamoDBAttribute(attributeName = DynamoConstants.MOBILE_NUMBER)
    private String mobileNumber;
}
