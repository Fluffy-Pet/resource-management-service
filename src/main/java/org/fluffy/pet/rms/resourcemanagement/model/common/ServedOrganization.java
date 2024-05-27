package org.fluffy.pet.rms.resourcemanagement.model.common;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServedOrganization {
    @Field(MongoConstants.ORGANIZATION_NAME)
    private String organizationName;

    @Field(MongoConstants.ROLE)
    private String role;

    @Field(MongoConstants.START_DATE)
    private LocalDate startDate;

    @Field(MongoConstants.END_DATE)
    private LocalDate endDate;
}
