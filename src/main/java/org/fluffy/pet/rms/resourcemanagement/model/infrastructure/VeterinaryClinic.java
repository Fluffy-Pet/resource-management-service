package org.fluffy.pet.rms.resourcemanagement.model.infrastructure;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.model.User;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@SuperBuilder
@Document(MongoConstants.VETERINARY_CLINIC_TABLE)
public class VeterinaryClinic extends User {
    @Field(MongoConstants.CLINIC_NAME)
    private String clinicName;

    @Field(MongoConstants.ADDRESS)
    private String address;

    @Field(MongoConstants.PHONE_NUMBER)
    private String phoneNumber;

    @Field(MongoConstants.SERVICES_OFFERED)
    private String servicesOffered; // e.g., surgery, vaccination, etc.

    @Field(MongoConstants.OPERATING_HOURS)
    private String operatingHours; // e.g., 9 AM - 5 PM
}
