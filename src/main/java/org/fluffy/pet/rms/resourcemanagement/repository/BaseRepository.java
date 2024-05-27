package org.fluffy.pet.rms.resourcemanagement.repository;

import org.fluffy.pet.rms.resourcemanagement.repository.common.CommonRepository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T, ID> {
    CommonRepository<T, ID> getCommonRepository();

    default T save(T t) {
        return getCommonRepository().save(t);
    }

    default Optional<T> findById(ID id) {
        return getCommonRepository().findById(id);
    }

    default void deleteById(ID id) {
        getCommonRepository().deleteById(id);
    }

    default List<T> findAllByIds(Iterable<ID> ids) {
        return getCommonRepository().findAllByIds(ids);
    }
}
