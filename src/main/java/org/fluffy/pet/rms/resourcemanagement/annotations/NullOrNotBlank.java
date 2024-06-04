package org.fluffy.pet.rms.resourcemanagement.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.fluffy.pet.rms.resourcemanagement.validation.NullOrNotBlankValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NullOrNotBlankValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface NullOrNotBlank {
    String message() default "must be null or not blank";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
