package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.AddressRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.DocumentRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.ServedOrganizationRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.volunteer.VolunteerRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.volunteer.VoluteerResponse;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;
import org.fluffy.pet.rms.resourcemanagement.model.common.IdentityDocument;
import org.fluffy.pet.rms.resourcemanagement.model.common.ServedOrganization;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Volunteer;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;

@Transformer
public class VolunteerTransformer {

    public IdentityDocument convertRequestToModel(DocumentRequest documentRequest){
        return IdentityDocument
                .builder()
                .type(documentRequest.getDocumentType())
                .idNumber(documentRequest.getIdNumber())
                .url(documentRequest.getDocumentUrl())
                .build();
    }


    public Address convertRequestToModel(AddressRequest addressRequest){
        return Address
                .builder()
                .street(addressRequest.getStreet())
                .city(addressRequest.getCity())
                .state(addressRequest.getState())
                .country(addressRequest.getCountry())
                .zipCode(addressRequest.getZipCode())
                .build();
    }

    public ServedOrganization convertRequestToModel(ServedOrganizationRequest servedOrganizationRequest){
        return ServedOrganization
                .builder()
                .organizationName(servedOrganizationRequest.getOrganizationName())
                .role(servedOrganizationRequest.getRole())
                .startDate(servedOrganizationRequest.getStartDate())
                .endDate(servedOrganizationRequest.getEndDate())
                .build();
    }
    public Volunteer convertRequestToModel(VolunteerRequest volunteerRequest){
        return Volunteer
                .builder()
                .firstName(volunteerRequest.getFirstName())
                .lastName(volunteerRequest.getLastName())
                .availability(volunteerRequest.getAvailability())
                .skills(volunteerRequest.getSkills())
                .identityDocuments(StreamUtils.emptyIfNull(volunteerRequest.getIdentityDocuments()).map(this::convertRequestToModel).toList())
                .address(ObjectUtils.transformIfNotNull(volunteerRequest.getAddress(), this::convertRequestToModel))
                .servedOrganizations(StreamUtils.emptyIfNull(volunteerRequest.getServedOrganizations()).map(this::convertRequestToModel).toList())
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
        volunteer.setIdentityDocuments(StreamUtils.emptyIfNull(volunteerRequest.getIdentityDocuments()).map(this::convertRequestToModel).toList());
        volunteer.setAddress(ObjectUtils.transformIfNotNull(volunteerRequest.getAddress(), this::convertRequestToModel));
        volunteer.setServedOrganizations(StreamUtils.emptyIfNull(volunteerRequest.getServedOrganizations()).map(this::convertRequestToModel).toList());
    }
}
