package org.fluffy.pet.rms.resourcemanagement.dto.request.pet;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.PetType;
import org.fluffy.pet.rms.resourcemanagement.util.Constants;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class PetRequest {
    @NotBlank
    private String name;

    @NotNull
    private PetType petType;

    @NotNull
    @URL
    private String profileUrl;

    @NotNull
    @Past
    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate dateOfBirth;
}
