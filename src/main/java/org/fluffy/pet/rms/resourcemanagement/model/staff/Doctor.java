package org.fluffy.pet.rms.resourcemanagement.model.staff;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.model.User;
import org.fluffy.pet.rms.resourcemanagement.model.common.Information;
import org.fluffy.pet.rms.resourcemanagement.model.common.ServedOrganization;
import org.fluffy.pet.rms.resourcemanagement.model.common.Specialization;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@Document(MongoConstants.DOCTOR_TABLE)
public class Doctor extends User {
    @Field(MongoConstants.SPECIALIZATION)
    private Specialization specialization;

    @Field(MongoConstants.EXPERIENCE)
    private Integer experience; // in years

    @Field(MongoConstants.INFORMATION)
    private Information information;

    @Field(MongoConstants.SERVED_ORGANIZATIONS)
    private List<ServedOrganization> servedOrganizations;
}
