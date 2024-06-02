package org.fluffy.pet.rms.resourcemanagement.service;

import org.fluffy.pet.rms.resourcemanagement.dto.request.volunteer.VolunteerRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.volunteer.VolunteerResponse;

public interface VolunteerService {
    VolunteerResponse createVolunteer(VolunteerRequest volunteerRequest);

    VolunteerResponse getVolunteer(String id);

    VolunteerResponse updateVolunteer(VolunteerRequest updatevolunteerRequest, String id);

    void deleteVolunteer(String id);
}
