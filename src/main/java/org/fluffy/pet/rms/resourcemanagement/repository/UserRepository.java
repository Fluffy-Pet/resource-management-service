package org.fluffy.pet.rms.resourcemanagement.repository;

import org.fluffy.pet.rms.resourcemanagement.model.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, String> {
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByMobile(String mobile);
}
