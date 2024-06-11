package org.fluffy.pet.rms.resourcemanagement.dto.response.pet;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;
import org.fluffy.pet.rms.resourcemanagement.util.Constants;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class PetResponse {
    private String name;

    private PetType petType;

    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate dateOfBirth;

    private String profileUrl;
}
