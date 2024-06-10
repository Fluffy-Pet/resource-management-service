package org.fluffy.pet.rms.resourcemanagement.repository;

import org.fluffy.pet.rms.resourcemanagement.model.User;
import org.fluffy.pet.rms.resourcemanagement.model.common.Email;
import org.fluffy.pet.rms.resourcemanagement.model.common.Mobile;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, String> {
    Optional<User> findUserByEmail(Email email);

    Optional<User> findUserByMobile(Mobile mobile);
}
