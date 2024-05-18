package org.fluffy.pet.rms.resourcemanagement.model.common;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
public class ServedOrganization {
    @Field("organization_name")
    private String organizationName;

    @Field("role")
    private String role;

    @Field("start_date")
    private Date startDate;

    @Field("end_date")
    private Date endDate;
}
