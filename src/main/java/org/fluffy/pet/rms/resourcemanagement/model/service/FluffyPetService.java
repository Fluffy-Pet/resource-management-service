package org.fluffy.pet.rms.resourcemanagement.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.enums.ServiceType;
import org.fluffy.pet.rms.resourcemanagement.model.BaseEntity;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Document(MongoConstants.ADMIN_TABLE)
public class FluffyPetService extends BaseEntity {
    @Field(MongoConstants.SERVICE_TYPE)
    private ServiceType serviceType;

    private String description;

    @Field(MongoConstants.PROVIDER)
    private ServiceProvider provider;

    private List<ServiceCharge> charges;
}
