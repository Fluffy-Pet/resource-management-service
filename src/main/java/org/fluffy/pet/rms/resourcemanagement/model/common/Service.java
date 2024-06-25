package org.fluffy.pet.rms.resourcemanagement.model.common;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;
import org.fluffy.pet.rms.resourcemanagement.enums.ServiceSubType;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Service {
    @Field(MongoConstants.SERVICE_SUB_TYPE)
    private ServiceSubType serviceSubType;

    @Field(MongoConstants.PET_TYPE)
    private PetType petType;
}
