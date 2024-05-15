package org.fluffy.pet.rms.resourcemanagement.repository;

import org.fluffy.pet.rms.resourcemanagement.repository.common.CommonRepository;

import java.util.Optional;

@SuppressWarnings("java:S119")
public interface BaseRepository<T, ID> {
    CommonRepository<T, ID> getCommonRepository();

    default T save(T t) {
        return getCommonRepository().save(t);
    }

    default Optional<T> findById(ID id) {
        return getCommonRepository().findById(id);
    }

    default boolean deleteById(ID id) {
        return getCommonRepository().deleteById(id);
    }
}
