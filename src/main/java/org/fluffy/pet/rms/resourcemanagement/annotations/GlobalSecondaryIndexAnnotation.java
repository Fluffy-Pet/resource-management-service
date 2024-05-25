package org.fluffy.pet.rms.resourcemanagement.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GlobalSecondaryIndexAnnotation {
    String indexName();

    String hashKey();

    String rangeKey() default "";
}
