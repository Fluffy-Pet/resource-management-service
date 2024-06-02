package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.EmailInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.MobileInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignInEmailPassword;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignupInput;
import org.fluffy.pet.rms.resourcemanagement.dto.request.clinic.ClinicRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.doctor.DoctorResponse;
import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Doctor;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Transformer
public class DoctorTransformer {
    private static final String COUNTRY_CODE = "+91";
    private final CommonTransformer commonTransformer;

    @Autowired
    public DoctorTransformer(CommonTransformer commonTransformer) {
        this.commonTransformer = commonTransformer;
    }
    public Clinic convertRequestToModel(ClinicRequest clinicRequest){
        return Clinic
                .builder()
                .clinicName(clinicRequest.getName())
                .address(ObjectUtils.transformIfNotNull(clinicRequest.getAddress(), commonTransformer::convertRequestToModel))
                .operatingHours(ObjectUtils.transformIfNotNull(clinicRequest.getOperatingHours(), commonTransformer::convertRequestToModel))
                .phoneNumber(clinicRequest.getPhoneNumber())
                .servicesOffered(StreamUtils.emptyIfNull(clinicRequest.getServicesOffered()).map(commonTransformer::convertRequestToModel).toList())
                .build();
    }

    public Doctor convertRequestToModel(DoctorRequest doctorRequest){
        return Doctor
                .builder()
                .firstName(doctorRequest.getFirstName())
                .lastName(doctorRequest.getLastName())
                .specialization(StreamUtils.emptyIfNull(doctorRequest.getSpecialization()).toList())
                .experience(doctorRequest.getExperience())
                .identityDocuments(StreamUtils.emptyIfNull(doctorRequest.getDocuments()).map(commonTransformer::convertRequestToModel).toList())
                .associatedClinics(StreamUtils.emptyIfNull(doctorRequest.getAssociatedClinics()).map(this::convertRequestToModel).toList())
                .address(ObjectUtils.transformIfNotNull(doctorRequest.getAddress(), commonTransformer::convertRequestToModel))
                .servedOrganizations(StreamUtils.emptyIfNull(doctorRequest.getServedOrganizations()).map(commonTransformer::convertRequestToModel).toList())
                .build();
    }

    public DoctorResponse convertModelToResponse(Doctor doctor){
        return DoctorResponse
                .builder()
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .specialization(doctor.getSpecialization())
                .experience(doctor.getExperience())
                .documents(StreamUtils.emptyIfNull(doctor.getIdentityDocuments()).map(commonTransformer::convertModelToResponse).toList())
                .associatedClinics(StreamUtils.emptyIfNull(doctor.getAssociatedClinics()).map(commonTransformer::convertModelToResponse).toList())
                .address(ObjectUtils.transformIfNotNull(doctor.getAddress(), commonTransformer::convertModelToResponse))
                .servedOrganizations(StreamUtils.emptyIfNull(doctor.getServedOrganizations()).map(commonTransformer::convertModelToResponse).toList())
                .build();
    }

    public void updateDoctor(Doctor doctor, DoctorRequest doctorRequest){
        doctor.setFirstName(doctorRequest.getFirstName());
        doctor.setLastName(doctorRequest.getLastName());
        doctor.setSpecialization(doctorRequest.getSpecialization());
        doctor.setExperience(doctorRequest.getExperience());
        doctor.setIdentityDocuments(StreamUtils.emptyIfNull(doctorRequest.getDocuments()).map(commonTransformer::convertRequestToModel).toList());
        doctor.setAssociatedClinics(StreamUtils.emptyIfNull(doctorRequest.getAssociatedClinics()).map(this::convertRequestToModel).toList());
        doctor.setAddress(ObjectUtils.transformIfNotNull(doctorRequest.getAddress(), commonTransformer::convertRequestToModel));
        doctor.setServedOrganizations(StreamUtils.emptyIfNull(doctorRequest.getServedOrganizations()).map(commonTransformer::convertRequestToModel).toList());
    }
    public SignupInput convertRequestToSignupInput(DoctorRequest doctorRequest){
        return new SignupInput(
                new EmailInput(doctorRequest.getEmail()),
                new MobileInput( COUNTRY_CODE,doctorRequest.getMobile()),
                doctorRequest.getPassword());
    }

    public SignInEmailPassword convertRequestToSignInEmailPassword(DoctorRequest doctorRequest){
        return new SignInEmailPassword(
                new EmailInput(doctorRequest.getEmail()),
                doctorRequest.getPassword());
    }
}
