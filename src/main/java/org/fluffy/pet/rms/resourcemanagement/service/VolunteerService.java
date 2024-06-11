package org.fluffy.pet.rms.resourcemanagement.service;

import org.fluffy.pet.rms.resourcemanagement.dto.request.volunteer.VolunteerRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.volunteer.VolunteerResponse;

public interface VolunteerService {
    VolunteerResponse getVolunteer(String id);

    VolunteerResponse updateCurrentVolunteer(VolunteerRequest updatevolunteerRequest);

    void deleteVolunteer(String id);
}
