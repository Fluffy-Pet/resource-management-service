package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.EmailInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.MobileInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignInEmailPassword;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignInMobilePassword;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.clinic.ClinicResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.*;
import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;
import org.fluffy.pet.rms.resourcemanagement.model.common.*;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;

@Transformer
public class CommonTransformer {
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
    public Service convertRequestToModel(ServiceRequest serviceRequest) {
        return Service
                .builder()
                .serviceGroup(serviceRequest.getServiceGroup())
                .serviceSubGroup(serviceRequest.getServiceSubGroup())
                .petCategory(serviceRequest.getPetCategory())
                .build();
    }

    public OperatingHours convertRequestToModel(OperatingHoursRequest operatingHours){
        return OperatingHours
                .builder()
                .workingDays(operatingHours.getWorkingDays())
                .startTime(operatingHours.getStartTime())
                .endTime(operatingHours.getEndTime())
                .build();
    }
    public IdentityDocument convertRequestToModel(DocumentRequest documentRequest){
        return IdentityDocument
                .builder()
                .type(documentRequest.getDocumentType())
                .idNumber(documentRequest.getIdNumber())
                .url(documentRequest.getDocumentUrl())
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

    public AddressResponse convertModelToResponse(Address address){
        return AddressResponse
                .builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .build();
    }

    public OperatingHoursResponse convertModelToResponse(OperatingHours operatingHours){
        return OperatingHoursResponse
                .builder()
                .workingDays(operatingHours.getWorkingDays())
                .startTime(operatingHours.getStartTime())
                .endTime(operatingHours.getEndTime())
                .build();
    }

    public ServiceResponse convertModelToResponse(Service service){
        return ServiceResponse
                .builder()
                .serviceGroup(service.getServiceGroup())
                .serviceSubGroup(service.getServiceSubGroup())
                .petCategory(service.getPetCategory())
                .build();
    }

    public DocumentResponse convertModelToResponse(IdentityDocument document){
        return DocumentResponse
                .builder()
                .documentType(document.getType())
                .idNumber(document.getIdNumber())
                .documentUrl(document.getUrl())
                .build();
    }

    public ClinicResponse convertModelToResponse(Clinic clinic){
        return ClinicResponse
                .builder()
                .name(clinic.getClinicName())
                .address(convertModelToResponse(clinic.getAddress()))
                .phoneNumber(clinic.getPhoneNumber())
                .openingHours(convertModelToResponse(clinic.getOperatingHours()))
                .servicesOffered(StreamUtils.emptyIfNull(clinic.getServicesOffered()).map(this::convertModelToResponse).toList())
                .build();
    }

    public ServedOrganizationResponse convertModelToResponse(ServedOrganization servedOrganization){
        return ServedOrganizationResponse
                .builder()
                .organizationName(servedOrganization.getOrganizationName())
                .role(servedOrganization.getRole())
                .startDate(servedOrganization.getStartDate())
                .endDate(servedOrganization.getEndDate())
                .build();
    }

    public SignInEmailPassword convertRequestToSignInEmailPassword(UserEmailRequest userEmailRequest, String password){
        return new SignInEmailPassword(
                new EmailInput(userEmailRequest.getEmail()),
                password
        );
    }

    public SignInMobilePassword convertRequestToSignInMobilePassword(UserMobileRequest userMobileRequest, String password){
        return new SignInMobilePassword(
                new MobileInput(userMobileRequest.getCountryCode(), userMobileRequest.getMobile()),
                password
        );
    }
}
