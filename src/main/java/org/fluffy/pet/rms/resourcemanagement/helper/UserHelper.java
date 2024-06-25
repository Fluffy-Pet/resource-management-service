package org.fluffy.pet.rms.resourcemanagement.helper;

import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserIdentity;
import org.fluffy.pet.rms.resourcemanagement.util.Result;

public interface UserHelper {
    Result<UserIdentity, ErrorCode> getUserIdentity(String userId, UserType userType);
}
