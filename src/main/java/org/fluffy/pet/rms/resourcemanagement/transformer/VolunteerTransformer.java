package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.volunteer.VolunteerRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.volunteer.VolunteerResponse;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Volunteer;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

@Transformer
public class VolunteerTransformer {
    private final CommonTransformer commonTransformer;

    @Autowired
    public VolunteerTransformer(CommonTransformer commonTransformer) {
        this.commonTransformer = commonTransformer;
    }

    public VolunteerResponse convertModelToResponse(Volunteer volunteer){
        return VolunteerResponse
                .builder()
                .firstName(volunteer.getFirstName())
                .lastName(volunteer.getLastName())
                .profileImageUrl(
                        ObjectUtils.transformIfNotNull(
                                ObjectUtils.transformIfNotNull(
                                        volunteer.getProfileImageFileName(),
                                        commonTransformer::convertFileNameToUrl
                                ),
                                URL::toString
                        )
                )
                .availability(volunteer.getAvailability())
                .skills(volunteer.getSkills())
                .identityDocuments(StreamUtils.emptyIfNull(volunteer.getIdentityDocuments()).map(commonTransformer::convertModelToResponse).toList())
                .address(ObjectUtils.transformIfNotNull(volunteer.getAddress(), commonTransformer::convertModelToResponse))
                .servedOrganizations(StreamUtils.emptyIfNull(volunteer.getServedOrganizations()).map(commonTransformer::convertModelToResponse).toList())
                .build();
    }

    public void updateVolunteer(Volunteer volunteer, VolunteerRequest volunteerRequest){
        volunteer.setFirstName(volunteerRequest.getFirstName());
        volunteer.setLastName(volunteerRequest.getLastName());
        volunteer.setProfileImageFileName(volunteerRequest.getProfileImageFileName());
        volunteer.setAvailability(volunteerRequest.getAvailability());
        volunteer.setSkills(volunteerRequest.getSkills());
        volunteer.setIdentityDocuments(StreamUtils.emptyIfNull(volunteerRequest.getIdentityDocuments()).map(commonTransformer::convertRequestToModel).toList());
        volunteer.setAddress(ObjectUtils.transformIfNotNull(volunteerRequest.getAddress(), commonTransformer::convertRequestToModel));
        volunteer.setServedOrganizations(StreamUtils.emptyIfNull(volunteerRequest.getServedOrganizations()).map(commonTransformer::convertRequestToModel).toList());
    }
}
