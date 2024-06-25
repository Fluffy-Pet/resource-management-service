package org.fluffy.pet.rms.resourcemanagement.transformer;

import manager.file.FileManager;
import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.clinic.ClinicResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.*;
import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;
import org.fluffy.pet.rms.resourcemanagement.model.common.*;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

@Transformer
public class CommonTransformer {
    private final FileManager fileManager;

    @Autowired
    public CommonTransformer(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public URL convertFileNameToUrl(String fileName) {
        return fileManager.getFile(fileName);
    }

    public Address convertRequestToModel(AddressRequest addressRequest) {
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
                .petType(serviceRequest.getPetType())
                .build();
    }

    public OperatingHours convertRequestToModel(OperatingHoursRequest operatingHours) {
        return OperatingHours
                .builder()
                .workingDays(operatingHours.getWorkingDays())
                .startTime(operatingHours.getStartTime())
                .endTime(operatingHours.getEndTime())
                .build();
    }

    public IdentityDocument convertRequestToModel(IdentityDocumentRequest identityDocumentRequest) {
        return IdentityDocument
                .builder()
                .type(identityDocumentRequest.getDocumentType())
                .idNumber(identityDocumentRequest.getIdNumber())
                .documentFileName(identityDocumentRequest.getDocumentFileName())
                .build();
    }

    public ServedOrganization convertRequestToModel(ServedOrganizationRequest servedOrganizationRequest) {
        return ServedOrganization
                .builder()
                .organizationName(servedOrganizationRequest.getOrganizationName())
                .role(servedOrganizationRequest.getRole())
                .startDate(servedOrganizationRequest.getStartDate())
                .endDate(servedOrganizationRequest.getEndDate())
                .build();
    }

    public AssociatedClinic convertRequestToModel(AssociatedClinicRequest associatedClinicRequest) {
        return AssociatedClinic
                .builder()
                .clinicIds(associatedClinicRequest.getClinicIds())
                .build();
    }

    public AddressResponse convertModelToResponse(Address address) {
        return AddressResponse
                .builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .build();
    }

    public OperatingHoursResponse convertModelToResponse(OperatingHours operatingHours) {
        return OperatingHoursResponse
                .builder()
                .workingDays(operatingHours.getWorkingDays())
                .startTime(operatingHours.getStartTime())
                .endTime(operatingHours.getEndTime())
                .build();
    }

    public ServiceResponse convertModelToResponse(Service service) {
        return ServiceResponse
                .builder()
                .serviceGroup(service.getServiceGroup())
                .serviceSubGroup(service.getServiceSubGroup())
                .petType(service.getPetType())
                .build();
    }

    public IdentityDocumentResponse convertModelToResponse(IdentityDocument document) {
        return IdentityDocumentResponse
                .builder()
                .documentType(document.getType())
                .idNumber(document.getIdNumber())
                .documentUrl(
                        ObjectUtils.transformIfNotNull(
                                ObjectUtils.transformIfNotNull(
                                        document.getDocumentFileName(),
                                        this::convertFileNameToUrl
                                ),
                                URL::toString
                        )
                )
                .build();
    }

    public ClinicResponse convertModelToResponse(Clinic clinic) {
        return ClinicResponse
                .builder()
                .clinicName(clinic.getClinicName())
                .address(convertModelToResponse(clinic.getAddress()))
                .openingHours(convertModelToResponse(clinic.getOperatingHours()))
                .servicesOffered(StreamUtils.emptyIfNull(clinic.getServicesOffered()).map(this::convertModelToResponse).toList())
                .build();
    }

    public ServedOrganizationResponse convertModelToResponse(ServedOrganization servedOrganization) {
        return ServedOrganizationResponse
                .builder()
                .organizationName(servedOrganization.getOrganizationName())
                .role(servedOrganization.getRole())
                .startDate(servedOrganization.getStartDate())
                .endDate(servedOrganization.getEndDate())
                .build();
    }

    public Email convertEmailRequestToModel(EmailRequest emailRequest) {
        return Email.builder().emailId(emailRequest.getEmailId()).build();
    }

    public Mobile convertMobileRequestToModel(MobileRequest mobileRequest) {
        return Mobile.builder().mobileNumber(mobileRequest.getMobileNumber()).countryCode(mobileRequest.getCountryCode()).build();
    }

    public EmailResponse convertModelToResponse(Email email) {
        return EmailResponse.builder().emailId(email.getEmailId()).build();
    }

    public MobileResponse convertModelToResponse(Mobile mobile) {
        return MobileResponse.builder().countryCode(mobile.getCountryCode()).mobileNumber(mobile.getMobileNumber()).build();
    }
}
