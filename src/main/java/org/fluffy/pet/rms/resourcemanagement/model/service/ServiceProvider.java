package org.fluffy.pet.rms.resourcemanagement.model.service;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceProvider {
    @Field(MongoConstants.PROVIDER_ID)
    private String providerId;

    @Field(MongoConstants.PROVIDER_ENTITY_ID)
    private String providerEntityId;
}
