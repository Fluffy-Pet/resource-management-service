package org.fluffy.pet.rms.resourcemanagement.model.common;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@SuperBuilder
public class Coordinates {
    @Field("latitude")
    private double latitude;

    @Field("longitude")
    private double longitude;
}
