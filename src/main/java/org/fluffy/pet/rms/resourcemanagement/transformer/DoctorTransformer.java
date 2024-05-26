package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.doctor.DoctorResponse;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;
import org.fluffy.pet.rms.resourcemanagement.model.common.Document;
import org.fluffy.pet.rms.resourcemanagement.model.common.ServedOrganization;
import org.fluffy.pet.rms.resourcemanagement.model.infrastructure.Clinic;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Doctor;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;

@Transformer
public class DoctorTransformer {

    public Document convertRequestToModel(DocumentRequest documentRequest){
        return Document
                .builder()
                .type(documentRequest.getDocumentType())
                .idNumber(documentRequest.getIdNumber())
                .url(documentRequest.getDocumentUrl())
                .build();
    }

    public Clinic convertRequestToModel(ClinicRequest clinicRequest){
        return Clinic
                .builder()
                .clinicName(clinicRequest.getName())
                .address(clinicRequest.getAddress())
                .operatingHours(clinicRequest.getOperatingHours())
                .phoneNumber(clinicRequest.getPhoneNumber())
                .servicesOffered(clinicRequest.getServicesOffered())
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
    public Doctor convertRequestToModel(DoctorRequest doctorRequest){
        return Doctor
                .builder()
                .firstName(doctorRequest.getFirstName())
                .lastName(doctorRequest.getLastName())
                .specialization(StreamUtils.emptyIfNull(doctorRequest.getSpecialization()).toList())
                .experience(doctorRequest.getExperience())
                .documents(StreamUtils.emptyIfNull(doctorRequest.getDocuments()).map(this::convertRequestToModel).toList())
                .associatedClinics(StreamUtils.emptyIfNull(doctorRequest.getAssociatedClinics()).map(this::convertRequestToModel).toList())
                .address(ObjectUtils.transformIfNotNull(doctorRequest.getAddress(), this::convertRequestToModel))
                .servedOrganizations(StreamUtils.emptyIfNull(doctorRequest.getServedOrganizations()).map(this::convertRequestToModel).toList())
                .build();
    }


    public DoctorResponse convertModelToResponse(Doctor doctor){
        return DoctorResponse
                .builder()
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .specialization(doctor.getSpecialization())
                .experience(doctor.getExperience())
                .documents(doctor.getDocuments())
                .associatedClinics(doctor.getAssociatedClinics())
                .address(doctor.getAddress().toString())
                .servedOrganizations(doctor.getServedOrganizations())
                .build();
    }


    public void updateDoctor(Doctor doctor, DoctorRequest doctorRequest){
        doctor.setFirstName(doctorRequest.getFirstName());
        doctor.setLastName(doctorRequest.getLastName());
        doctor.setSpecialization(doctorRequest.getSpecialization());
        doctor.setExperience(doctorRequest.getExperience());
        doctor.setDocuments(StreamUtils.emptyIfNull(doctorRequest.getDocuments()).map(this::convertRequestToModel).toList());
        doctor.setAssociatedClinics(StreamUtils.emptyIfNull(doctorRequest.getAssociatedClinics()).map(this::convertRequestToModel).toList());
        doctor.setAddress(ObjectUtils.transformIfNotNull(doctorRequest.getAddress(), this::convertRequestToModel));
        doctor.setServedOrganizations(StreamUtils.emptyIfNull(doctorRequest.getServedOrganizations()).map(this::convertRequestToModel).toList());
    }
}
