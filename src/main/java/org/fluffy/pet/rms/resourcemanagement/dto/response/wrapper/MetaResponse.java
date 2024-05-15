package org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.ResponseStatus;
import org.fluffy.pet.rms.resourcemanagement.util.Constants;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class MetaResponse {
    private ResponseStatus status;

    @JsonFormat(pattern = Constants.RESPONSE_TIMESTAMP_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDateTime timestamp;
}
