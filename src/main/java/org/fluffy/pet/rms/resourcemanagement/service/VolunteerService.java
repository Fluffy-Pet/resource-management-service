package org.fluffy.pet.rms.resourcemanagement.service;

import org.fluffy.pet.rms.resourcemanagement.dto.request.volunteer.VolunteerRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.volunteer.VoluteerResponse;

public interface VolunteerService {
    VoluteerResponse createVolunteer(VolunteerRequest volunteerRequest);

    VoluteerResponse getVolunteer(String id);

    VoluteerResponse updateVolunteer(VolunteerRequest updatevolunteerRequest, String id);

    void deleteVolunteer(String id);
}
