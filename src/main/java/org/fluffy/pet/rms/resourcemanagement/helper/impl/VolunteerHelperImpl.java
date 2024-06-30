package org.fluffy.pet.rms.resourcemanagement.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.fluffy.pet.rms.resourcemanagement.annotations.Helper;
import org.fluffy.pet.rms.resourcemanagement.dto.response.volunteer.VolunteerResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.helper.VolunteerHelper;
import org.fluffy.pet.rms.resourcemanagement.model.common.ProviderIdentity;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserIdentity;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Volunteer;
import org.fluffy.pet.rms.resourcemanagement.repository.VolunteerRepository;
import org.fluffy.pet.rms.resourcemanagement.transformer.VolunteerTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.util.Optional;

@Helper
@Slf4j
public class VolunteerHelperImpl implements VolunteerHelper {
    private final VolunteerRepository volunteerRepository;

    private final VolunteerTransformer volunteerTransformer;

    @Autowired
    public VolunteerHelperImpl(VolunteerRepository volunteerRepository, VolunteerTransformer volunteerTransformer) {
        this.volunteerRepository = volunteerRepository;
        this.volunteerTransformer = volunteerTransformer;
    }

    @Override
    public Result<Void, ErrorCode> createUserEntityForIdOnly(String userId) {
        Volunteer volunteer = Volunteer.builder().id(userId).status(Status.ACTIVE).build();
        try {
            Volunteer createdVolunteer = volunteerRepository.save(volunteer);
            log.info("Successfully created empty volunteer with id {}", createdVolunteer.getId());
            return Result.success(null);
        } catch (DuplicateKeyException duplicateKeyException) {
            return Result.error(ErrorCode.DUPLICATE_USER);
        }
    }

    @Override
    public boolean checkUserEntityExists(String userEntityId) {
        return volunteerRepository.exists(userEntityId);
    }

    @Override
    public Result<VolunteerResponse, ErrorCode> getUserEntityById(String userId) {
        Optional<Volunteer> optionalVolunteer = volunteerRepository.findById(userId);
        if (optionalVolunteer.isEmpty()) {
            return Result.error(ErrorCode.VOLUNTEER_NOT_FOUND);
        }
        Volunteer volunteer = optionalVolunteer.get();
        return Result.success(volunteerTransformer.convertModelToResponse(volunteer));
    }

    @Override
    public Result<UserIdentity, ErrorCode> getUserIdentity(String userId) {
        Optional<Volunteer> optionalVolunteer = volunteerRepository.findById(userId);
        if (optionalVolunteer.isEmpty()) {
            return Result.error(ErrorCode.VOLUNTEER_NOT_FOUND);
        }
        return Result.success(volunteerTransformer.convertModelToIdentity(optionalVolunteer.get()));
    }

    @Override
    public Result<Void, ErrorCode> updateProfilePicture(String userId, String profilePictureFileName) {
        Optional<Volunteer> optionalVolunteer = volunteerRepository.findById(userId);
        if (optionalVolunteer.isEmpty()) {
            return Result.error(ErrorCode.VOLUNTEER_NOT_FOUND);
        }
        Volunteer volunteer = optionalVolunteer.get();
        volunteer.setProfileImageFileName(profilePictureFileName);
        volunteerRepository.save(volunteer);
        return Result.success(null);
    }

    @Override
    public Result<ProviderIdentity, ErrorCode> getProviderIdentityById(String volunteerId) {
        Optional<Volunteer> optionalVolunteer = volunteerRepository.findById(volunteerId);
        if (optionalVolunteer.isEmpty()) {
            return Result.error(ErrorCode.VOLUNTEER_NOT_FOUND);
        }
        return Result.success(volunteerTransformer.convertModelToProviderIdentity(optionalVolunteer.get()));
    }
}
