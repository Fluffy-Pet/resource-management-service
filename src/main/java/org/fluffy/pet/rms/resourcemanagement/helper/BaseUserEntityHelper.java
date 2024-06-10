package org.fluffy.pet.rms.resourcemanagement.helper;

import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.util.Result;

public interface BaseUserEntityHelper {
    Result<Void, ErrorCode> createUserEntityForIdOnly(String userId);

    boolean checkUserEntityExists(String userEntityId);
}
