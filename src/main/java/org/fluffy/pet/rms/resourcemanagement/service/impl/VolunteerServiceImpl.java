package org.fluffy.pet.rms.resourcemanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.UserEmailRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.UserMobileRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.volunteer.VolunteerRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.volunteer.VolunteerResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.helper.UserHelper;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Volunteer;
import org.fluffy.pet.rms.resourcemanagement.repository.VolunteerRepository;
import org.fluffy.pet.rms.resourcemanagement.service.VolunteerService;
import org.fluffy.pet.rms.resourcemanagement.transformer.VolunteerTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public <Q> VolunteerResponse createVolunteer(VolunteerRequest<Q> volunteerRequest) {
        Volunteer volunteer = volunteerTransformer.convertRequestToModel(volunteerRequest);

        String password = volunteerRequest.getPassword();
        Optional<String> userId = switch (volunteerRequest.getSignupUserInfo()) {
            case UserEmailRequest userEmailRequest -> UserUtils.fetchUserIdForSignup(
                    volunteerRequest,
                    volunteerTransformer.convertRequestToSignInEmailPassword(userEmailRequest, password),
                    volunteerTransformer::convertRequestToSignupInput,
                    userHelper::signup,
                    userHelper::signIn
            );
            case UserMobileRequest userMobileRequest -> UserUtils.fetchUserIdForSignup(
                    volunteerRequest,
                    volunteerTransformer.convertRequestToSignInMobilePassword(userMobileRequest, password),
                    volunteerTransformer::convertRequestToSignupInput,
                    userHelper::signup,
                    userHelper::signIn
            );
            default -> throw new RestException(HttpStatus.BAD_REQUEST, ErrorResponse.from(ErrorCode.INPUT_VALIDATION_ERROR));
        };
        if (userId.isEmpty()) {
            throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorResponse.from(ErrorCode.INTERNAL_SERVER_ERROR));
        }
        volunteer.setId(userId.get());
        volunteer.setStatus(Status.ACTIVE);
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
    public <T> VolunteerResponse updateVolunteer(VolunteerRequest<T> updatevolunteerRequest, String id) {
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
