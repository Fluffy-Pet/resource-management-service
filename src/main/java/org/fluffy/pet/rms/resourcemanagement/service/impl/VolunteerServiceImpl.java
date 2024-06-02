package org.fluffy.pet.rms.resourcemanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignInEmailPassword;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignupInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignInOutput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignupOutput;
import org.fluffy.pet.rms.resourcemanagement.dto.request.volunteer.VolunteerRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.volunteer.VolunteerResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.helper.UserHelper;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Volunteer;
import org.fluffy.pet.rms.resourcemanagement.repository.VolunteerRepository;
import org.fluffy.pet.rms.resourcemanagement.service.VolunteerService;
import org.fluffy.pet.rms.resourcemanagement.transformer.VolunteerTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode.DUPLICATE_USER;

@Service
@Slf4j
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository volunteerRepository;

    private final VolunteerTransformer volunteerTransformer;

    private final UserHelper userHelper;

    @Autowired
    public VolunteerServiceImpl(VolunteerRepository volunteerRepository, VolunteerTransformer volunteerTransformer,UserHelper userHelper) {
        this.volunteerRepository = volunteerRepository;
        this.volunteerTransformer = volunteerTransformer;
        this.userHelper=userHelper;
    }


    @Override
    public VolunteerResponse createVolunteer(VolunteerRequest volunteerRequest) {
        Volunteer volunteer = volunteerTransformer.convertRequestToModel(volunteerRequest);
        SignupInput signupInput=volunteerTransformer.convertRequestToSignupInput(volunteerRequest);
        Result<SignupOutput, ErrorCode> result = userHelper.signup(signupInput);
        if (result.isSuccess()) {
            volunteer.setId(result.getData().userId());
        } else if(result.getError().equals(DUPLICATE_USER))
        {
            SignInEmailPassword signInEmailPassword = volunteerTransformer.convertRequestToSignInEmailPassword(volunteerRequest);
            Result<SignInOutput, ErrorCode> signInResult = userHelper.signIn(signInEmailPassword);
            volunteer.setId(signInResult.getData().userId());
        }
        try {
            Volunteer createdVolunteer = volunteerRepository.save(volunteer);
            return volunteerTransformer.convertModelToResponse(createdVolunteer);
        } catch (DuplicateKeyException e) {
            log.error(String.format("Exception happened in creating user for %s", volunteerRequest.getFirstName()), e
            );
            throw new RestException(HttpStatus.BAD_REQUEST, ErrorResponse.from(ErrorCode.VOLUNTEER_ALREADY_EXISTS));
        }
    }

    @Override
    public VolunteerResponse getVolunteer(String id) {
        Volunteer volunteer = volunteerRepository.findById(id).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.VOLUNTEER_NOT_FOUND))
        );
        return volunteerTransformer.convertModelToResponse(volunteer);
    }

    @Override
    public VolunteerResponse updateVolunteer(VolunteerRequest updatevolunteerRequest, String id) {
        Volunteer volunteer = volunteerRepository.findById(id).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.VOLUNTEER_NOT_FOUND))
        );
        volunteerTransformer.updateVolunteer(volunteer, updatevolunteerRequest);
        Volunteer updatedVolunteer = volunteerRepository.save(volunteer);
        return volunteerTransformer.convertModelToResponse(updatedVolunteer);
    }

    @Override
    public void deleteVolunteer(String id) {
    volunteerRepository.deleteById(id);
    }
}
