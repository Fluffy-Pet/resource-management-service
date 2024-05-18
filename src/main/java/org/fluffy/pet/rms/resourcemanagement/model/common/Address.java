package org.fluffy.pet.rms.resourcemanagement.model.common;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@SuperBuilder
public class Address {
    @Field("street")
    private String street;

    @Field("city")
    private String city;

    @Field("state")
    private String state;

    @Field("zip_code")
    private String zipCode;

    @Field("country")
    private String country;
}
