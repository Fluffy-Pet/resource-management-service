package org.fluffy.pet.rms.resourcemanagement.repository.common;

import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterNode;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.SortCriteria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CommonRepository<T, ID> {
    T save(T t);

    Optional<T> findById(ID id);

    void deleteById(ID id);

    List<T> findAllByIds(Iterable<ID> ids);

    boolean exists(String id);

    Page<T> filterDocuments(
            FilterNode filterNode,
            List<SortCriteria> sortCriteriaList,
            Integer page,
            Integer pageSize
    );
}
