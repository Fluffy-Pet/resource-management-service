package org.fluffy.pet.rms.resourcemanagement.helper.impl;

import org.fluffy.pet.rms.resourcemanagement.annotations.Helper;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.helper.DoctorHelper;
import org.fluffy.pet.rms.resourcemanagement.util.Result;

@Helper
public class DoctorHelperImpl implements DoctorHelper {
    @Override
    public Result<Void, ErrorCode> createUserEntityForIdOnly(String userId) {
        return null;
    }

    @Override
    public boolean checkUserEntityExists(String userEntityId) {
        return false;
    }
}
