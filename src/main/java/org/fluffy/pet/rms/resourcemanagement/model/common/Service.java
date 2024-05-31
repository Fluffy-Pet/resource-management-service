package org.fluffy.pet.rms.resourcemanagement.model.common;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.PetCategory;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Service {
    @Field(MongoConstants.SERVICE_GROUP)
    private String serviceGroup;

    @Field(MongoConstants.SERVICE_SUB_GROUP)
    private String serviceSubGroup;

    @Field(MongoConstants.PET_CATEGORY)
    private PetCategory petCategory;
}
