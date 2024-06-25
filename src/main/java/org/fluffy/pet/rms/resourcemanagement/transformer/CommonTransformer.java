package org.fluffy.pet.rms.resourcemanagement.transformer;

import manager.file.FileManager;
import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.clinic.ClinicResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.service.ProviderIdentityResponse;
import org.fluffy.pet.rms.resourcemanagement.model.animal.Pet;
import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;
import org.fluffy.pet.rms.resourcemanagement.model.common.*;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
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

    public Owner convertUserIdToOwner(String userId) {
        return Owner.builder().userId(userId).build();
    }

    public ProviderIdentityResponse convertModelToIdentity(ProviderIdentity providerIdentity) {
        return ProviderIdentityResponse
                .builder()
                .providerId(providerIdentity.getProviderId())
                .name(providerIdentity.getName())
                .profileUrl(
                        ObjectUtils.transformIfNotNull(
                                ObjectUtils.transformIfNotNull(
                                        providerIdentity.getProfileImageFileName(),
                                        this::convertFileNameToUrl
                                ),
                                URL::toString
                        )
                )
                .build();
    }

    public PetIdentityResponse convertModelToIdentity(Pet pet) {
        return PetIdentityResponse
                .builder()
                .petId(pet.getId())
                .petName(pet.getName())
                .petType(pet.getPetType())
                .profilePicture(
                        ObjectUtils.transformIfNotNull(
                                ObjectUtils.transformIfNotNull(
                                        pet.getProfileImageFileName(),
                                        this::convertFileNameToUrl
                                ),
                                URL::toString
                        )
                )
                .build();
    }

    public UserIdentityResponse convertModelToIdentity(UserIdentity userIdentity) {
        return UserIdentityResponse
                .builder()
                .userId(userIdentity.getUserId())
                .firstName(userIdentity.getFirstName())
                .lastName(userIdentity.getLastName())
                .profilePhoto(
                        ObjectUtils.transformIfNotNull(
                                ObjectUtils.transformIfNotNull(
                                        userIdentity.getProfilePhotoFileName(),
                                        this::convertFileNameToUrl
                                ),
                                URL::toString
                        )
                )
                .build();
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
                .serviceSubType(serviceRequest.getServiceSubType())
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
                .serviceSubType(service.getServiceSubType())
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
