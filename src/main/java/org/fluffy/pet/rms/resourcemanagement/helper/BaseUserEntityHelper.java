package org.fluffy.pet.rms.resourcemanagement.helper;

import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserIdentity;
import org.fluffy.pet.rms.resourcemanagement.util.Result;

public interface BaseUserEntityHelper<T> {
    Result<Void, ErrorCode> createUserEntityForIdOnly(String userId);

    boolean checkUserEntityExists(String userEntityId);

    Result<T, ErrorCode> getUserEntityById(String userId);

    Result<UserIdentity, ErrorCode> getUserIdentity(String userId);

    Result<Void, ErrorCode> updateProfilePicture(String userId, String profilePictureFileName);
}
