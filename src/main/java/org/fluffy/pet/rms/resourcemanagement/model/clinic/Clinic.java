package org.fluffy.pet.rms.resourcemanagement.model.clinic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.model.BaseEntity;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;
import org.fluffy.pet.rms.resourcemanagement.model.common.OperatingHours;
import org.fluffy.pet.rms.resourcemanagement.model.common.Service;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Document(MongoConstants.CLINIC_TABLE)
public class Clinic extends BaseEntity {
    @Field(MongoConstants.CLINIC_NAME)
    private String clinicName;

    @Field(MongoConstants.CLINIC_DESCRIPTION)
    private String description;

    @Field(MongoConstants.ADDRESS)
    private Address address;

    @Field(MongoConstants.SERVICES_OFFERED)
    private List<Service> servicesOffered;

    @Field(MongoConstants.OPERATING_HOURS)
    private OperatingHours operatingHours;
}
