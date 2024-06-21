package org.fluffy.pet.rms.resourcemanagement.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.volunteer.VolunteerRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.volunteer.VolunteerResponse;
import org.fluffy.pet.rms.resourcemanagement.util.PaginationWrapper;

import java.util.List;

public interface VolunteerService {
    VolunteerResponse getVolunteer(String id);

    VolunteerResponse updateCurrentVolunteer(VolunteerRequest updatevolunteerRequest);

    void deleteVolunteer(String id);

    PaginationWrapper<List<JsonNode>> filterVolunteers(FilterRequest filterRequest);
}
