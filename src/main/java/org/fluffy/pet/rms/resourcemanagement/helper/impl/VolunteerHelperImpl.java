package org.fluffy.pet.rms.resourcemanagement.helper.impl;

import org.fluffy.pet.rms.resourcemanagement.annotations.Helper;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.helper.VolunteerHelper;
import org.fluffy.pet.rms.resourcemanagement.util.Result;

@Helper
public class VolunteerHelperImpl implements VolunteerHelper {
    @Override
    public Result<Void, ErrorCode> createUserEntityForIdOnly(String userId) {
        return null;
    }

    @Override
    public boolean checkUserEntityExists(String userEntityId) {
        return false;
    }
}
