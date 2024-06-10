package org.fluffy.pet.rms.resourcemanagement.annotations;

import org.fluffy.pet.rms.resourcemanagement.enums.UserType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckAccess {
    UserType[] values();
}

