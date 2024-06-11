package org.fluffy.pet.rms.resourcemanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.fluffy.pet.rms.resourcemanagement.configuration.contexts.UserContext;
import org.fluffy.pet.rms.resourcemanagement.dto.request.volunteer.VolunteerRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.volunteer.VolunteerResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Volunteer;
import org.fluffy.pet.rms.resourcemanagement.repository.VolunteerRepository;
import org.fluffy.pet.rms.resourcemanagement.service.VolunteerService;
import org.fluffy.pet.rms.resourcemanagement.transformer.VolunteerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository volunteerRepository;

    private final VolunteerTransformer volunteerTransformer;

    private final UserContext userContext;

    @Autowired
    public VolunteerServiceImpl(VolunteerRepository volunteerRepository, VolunteerTransformer volunteerTransformer, UserContext userContext) {
        this.volunteerRepository = volunteerRepository;
        this.volunteerTransformer = volunteerTransformer;
        this.userContext = userContext;
    }

    @Override
    public VolunteerResponse getVolunteer(String id) {
        Volunteer volunteer = volunteerRepository.findById(id).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.VOLUNTEER_NOT_FOUND))
        );
        return volunteerTransformer.convertModelToResponse(volunteer);
    }

    @Override
    public VolunteerResponse updateCurrentVolunteer(VolunteerRequest updatevolunteerRequest) {
        Volunteer volunteer = volunteerRepository.findById(userContext.getUserId()).orElseThrow(
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
