package org.fluffy.pet.rms.resourcemanagement.model.common;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.WorkingDays;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OperatingHours {
    @Field(MongoConstants.WORKING_DAYS)
    private List<WorkingDays> workingDays;

    @Field(MongoConstants.START_TIME)
    private LocalTime startTime;

    @Field(MongoConstants.END_TIME)
    private LocalTime endTime;
}
