package org.fluffy.pet.rms.resourcemanagement.repository.common;

import java.util.List;
import java.util.Optional;

public interface CommonRepository<T, ID> {
    void save(T t);

    Optional<T> findById(ID id);

    boolean deleteById(ID id);

    List<T> findAllByIds(Iterable<ID> ids);
}
