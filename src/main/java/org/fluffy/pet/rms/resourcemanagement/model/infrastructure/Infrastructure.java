package org.fluffy.pet.rms.resourcemanagement.model.infrastructure;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.model.BaseEntity;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@Document(MongoConstants.INFRASTRUCTURE_TABLE)
public class Infrastructure extends BaseEntity {
    @Field(MongoConstants.INFRASTRUCTURE_NAME)
    private String name;

    @Field(MongoConstants.INFRASTRUCTURE_DESCRIPTION)
    private String description;

    @Field(MongoConstants.INFRASTRUCTURE_SERVICE)
    private List<Service> services;

    @Field(MongoConstants.INFRASTRUCTURE_TYPE)
    private String type;

    @Field(MongoConstants.INFRASTRUCTURE_SUB_TYPE)
    private String subType;
}
