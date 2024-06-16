package org.fluffy.pet.rms.resourcemanagement.model.service;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.ServiceProviderType;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServiceProvider {
    private ServiceProviderType providerType;

    @Field(MongoConstants.PROVIDER_ID)
    private String providerId;
}
