package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.EmailInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.MobileInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignInEmailPassword;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignupInput;
import org.fluffy.pet.rms.resourcemanagement.dto.request.volunteer.VolunteerRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.volunteer.VolunteerResponse;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Volunteer;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Transformer
public class VolunteerTransformer {
    private static final String COUNTRY_CODE = "+91";
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

    public VolunteerResponse convertModelToResponse(Volunteer volunteer){
        return VolunteerResponse
                .builder()
                .firstName(volunteer.getFirstName())
                .lastName(volunteer.getLastName())
                .availability(volunteer.getAvailability())
                .skills(volunteer.getSkills())
                .identityDocuments(volunteer.getIdentityDocuments())
                .address(volunteer.getAddress())
                .servedOrganizations(StreamUtils.emptyIfNull(volunteer.getServedOrganizations()).map(commonTransformer::convertModelToResponse).toList())
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

    public SignupInput convertRequestToSignupInput(VolunteerRequest volunteerRequest){
        return new SignupInput(
                new EmailInput(volunteerRequest.getEmail()),
                new MobileInput( COUNTRY_CODE,volunteerRequest.getMobile()),
                volunteerRequest.getPassword());
    }

    public SignInEmailPassword convertRequestToSignInEmailPassword(VolunteerRequest volunteerRequest){
        return new SignInEmailPassword(
                new EmailInput(volunteerRequest.getEmail()),
                volunteerRequest.getPassword());
    }
}
