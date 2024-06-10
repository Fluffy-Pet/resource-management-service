package org.fluffy.pet.rms.resourcemanagement.repository;

import org.fluffy.pet.rms.resourcemanagement.model.User;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserEmail;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserMobile;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, String> {
    Optional<User> findUserByEmail(UserEmail userEmail);

    Optional<User> findUserByMobile(UserMobile userMobile);
}
