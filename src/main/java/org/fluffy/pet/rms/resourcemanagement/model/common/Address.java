package org.fluffy.pet.rms.resourcemanagement.model.common;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Address {
    @Field(MongoConstants.STREET)
    private String street;

    @Field(MongoConstants.CITY)
    private String city;

    @Field(MongoConstants.STATE)
    private String state;

    @Field(MongoConstants.ZIP_CODE)
    private String zipCode;

    @Field(MongoConstants.COUNTRY)
    private String country;

    @Field(MongoConstants.COORDINATES)
    private Coordinates coordinates;
}
