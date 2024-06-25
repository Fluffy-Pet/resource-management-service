package org.fluffy.pet.rms.resourcemanagement.helper.impl;

import org.fluffy.pet.rms.resourcemanagement.annotations.Helper;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;
import org.fluffy.pet.rms.resourcemanagement.helper.*;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserIdentity;
import org.fluffy.pet.rms.resourcemanagement.util.Result;
import org.springframework.beans.factory.annotation.Autowired;

@Helper
public class UserHelperImpl implements UserHelper {
    private final AdminHelper adminHelper;

    private final ClientHelper clientHelper;

    private final DoctorHelper doctorHelper;

    private final VolunteerHelper volunteerHelper;

    @Autowired
    public UserHelperImpl(AdminHelper adminHelper, ClientHelper clientHelper, DoctorHelper doctorHelper, VolunteerHelper volunteerHelper) {
        this.adminHelper = adminHelper;
        this.clientHelper = clientHelper;
        this.doctorHelper = doctorHelper;
        this.volunteerHelper = volunteerHelper;
    }

    @Override
    public Result<UserIdentity, ErrorCode> getUserIdentity(String userId, UserType userType) {
        return switch (userType) {
            case ADMIN -> adminHelper.getUserIdentity(userId);
            case CLIENT -> clientHelper.getUserIdentity(userId);
            case DOCTOR -> doctorHelper.getUserIdentity(userId);
            case VOLUNTEER -> volunteerHelper.getUserIdentity(userId);
        };
    }
}
