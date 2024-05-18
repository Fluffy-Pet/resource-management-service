package org.fluffy.pet.rms.resourcemanagement.model.common;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class Specialization {
    @Field("pet_types")
    private List<PetType> petTypes;
}
