package org.fluffy.pet.rms.resourcemanagement.model.common;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OperatingHours {
    @Field(MongoConstants.START_TIME)
    private LocalDateTime startTime;

    @Field(MongoConstants.END_TIME)
    private LocalDateTime endTime;
}
