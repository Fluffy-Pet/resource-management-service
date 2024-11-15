package org.fluffy.pet.rms.resourcemanagement.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StreamUtils {
    public static <T> Stream<T> emptyIfNull(Collection<T> collection) {
        if (collection == null) {
            return Stream.empty();
        }
        return collection.stream();
    }

    public static <T> Stream<T> emptyIfNull(Collection<T> collection, boolean parallel) {
        if (collection == null) {
            return Stream.empty();
        }
        return parallel ? collection.parallelStream() : collection.stream();
    }
}
