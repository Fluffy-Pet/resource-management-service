package org.fluffy.pet.rms.resourcemanagement.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ObjectUtils {
    public static <T, R> R transformIfNotNull(T t, Function<T, R> transformer) {
        if (t == null) {
            return null;
        }
        return transformer.apply(t);
    }
}
