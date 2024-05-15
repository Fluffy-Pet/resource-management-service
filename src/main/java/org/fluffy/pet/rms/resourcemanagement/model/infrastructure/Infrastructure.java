package org.fluffy.pet.rms.resourcemanagement.model.infrastructure;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.model.BaseEntity;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@Document(MongoConstants.INFRASTRUCTURE_TABLE)
public class Infrastructure extends BaseEntity {
    private String name;

    private String description;

    private List<Service> services;

    private String type;

    private String subType;
}
