package org.fluffy.pet.rms.resourcemanagement.model.common;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ContactInformation {
    @Field(MongoConstants.MOBILE)
    private String phoneNumber;

    @Field(MongoConstants.EMAIL_ID)
    private String emailAddress;
}
