package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.volunteer.VolunteerRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.volunteer.VoluteerResponse;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Volunteer;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Transformer
public class VolunteerTransformer {
    private final CommonTransformer commonTransformer;

    @Autowired
    public VolunteerTransformer(CommonTransformer commonTransformer) {
        this.commonTransformer = commonTransformer;
    }
    public Volunteer convertRequestToModel(VolunteerRequest volunteerRequest){
        return Volunteer
                .builder()
                .firstName(volunteerRequest.getFirstName())
                .lastName(volunteerRequest.getLastName())
                .availability(volunteerRequest.getAvailability())
                .skills(volunteerRequest.getSkills())
                .identityDocuments(StreamUtils.emptyIfNull(volunteerRequest.getIdentityDocuments()).map(commonTransformer::convertRequestToModel).toList())
                .address(ObjectUtils.transformIfNotNull(volunteerRequest.getAddress(), commonTransformer::convertRequestToModel))
                .servedOrganizations(StreamUtils.emptyIfNull(volunteerRequest.getServedOrganizations()).map(commonTransformer::convertRequestToModel).toList())
                .build();
    }

    public VoluteerResponse convertModelToResponse(Volunteer volunteer){
        return VoluteerResponse
                .builder()
                .firstName(volunteer.getFirstName())
                .lastName(volunteer.getLastName())
                .availability(volunteer.getAvailability())
                .skills(volunteer.getSkills())
                .identityDocuments(volunteer.getIdentityDocuments())
                .address(volunteer.getAddress())
                .servedOrganizations(volunteer.getServedOrganizations())
                .build();
    }

    public void updateVolunteer(Volunteer volunteer, VolunteerRequest volunteerRequest){
        volunteer.setFirstName(volunteerRequest.getFirstName());
        volunteer.setLastName(volunteerRequest.getLastName());
        volunteer.setAvailability(volunteerRequest.getAvailability());
        volunteer.setSkills(volunteerRequest.getSkills());
        volunteer.setIdentityDocuments(StreamUtils.emptyIfNull(volunteerRequest.getIdentityDocuments()).map(commonTransformer::convertRequestToModel).toList());
        volunteer.setAddress(ObjectUtils.transformIfNotNull(volunteerRequest.getAddress(), commonTransformer::convertRequestToModel));
        volunteer.setServedOrganizations(StreamUtils.emptyIfNull(volunteerRequest.getServedOrganizations()).map(commonTransformer::convertRequestToModel).toList());
    }
}
