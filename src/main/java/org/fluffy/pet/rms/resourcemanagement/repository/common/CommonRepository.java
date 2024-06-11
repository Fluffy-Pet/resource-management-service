package org.fluffy.pet.rms.resourcemanagement.repository.common;

import java.util.List;
import java.util.Optional;

public interface CommonRepository<T, ID> {
    T save(T t);

    Optional<T> findById(ID id);

    void deleteById(ID id);

    List<T> findAllByIds(Iterable<ID> ids);

    boolean exists(String id);
}
