package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.*;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.UserEmailRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.UserMobileRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.doctor.DoctorResponse;
import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Doctor;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Transformer
public class DoctorTransformer {
    private static final String COUNTRY_CODE = "+91";

    private final CommonTransformer commonTransformer;

    @Autowired
    public DoctorTransformer(CommonTransformer commonTransformer) {
        this.commonTransformer = commonTransformer;
    }

    public <T> Doctor convertRequestToModel(DoctorRequest<T> doctorRequest){
        return Doctor
                .builder()
                .firstName(doctorRequest.getFirstName())
                .lastName(doctorRequest.getLastName())
                .specialization(StreamUtils.emptyIfNull(doctorRequest.getSpecialization()).toList())
                .experience(doctorRequest.getExperience())
                .identityDocuments(StreamUtils.emptyIfNull(doctorRequest.getDocuments()).map(commonTransformer::convertRequestToModel).toList())
                .associatedClinics(StreamUtils.emptyIfNull(doctorRequest.getAssociatedClinics()).map(commonTransformer::convertRequestToModel).toList())
                .address(ObjectUtils.transformIfNotNull(doctorRequest.getAddress(), commonTransformer::convertRequestToModel))
                .servedOrganizations(StreamUtils.emptyIfNull(doctorRequest.getServedOrganizations()).map(commonTransformer::convertRequestToModel).toList())
                .build();
    }

    public DoctorResponse convertModelToResponse(Doctor doctor, List<Clinic> clinics){
        return DoctorResponse
                .builder()
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .specialization(doctor.getSpecialization())
                .experience(doctor.getExperience())
                .documents(StreamUtils.emptyIfNull(doctor.getIdentityDocuments()).map(commonTransformer::convertModelToResponse).toList())
                .associatedClinics(StreamUtils.emptyIfNull(clinics).map(commonTransformer::convertModelToResponse).toList())
                .address(ObjectUtils.transformIfNotNull(doctor.getAddress(), commonTransformer::convertModelToResponse))
                .servedOrganizations(StreamUtils.emptyIfNull(doctor.getServedOrganizations()).map(commonTransformer::convertModelToResponse).toList())
                .build();
    }

    public <T> void updateDoctor(Doctor doctor, DoctorRequest<T> doctorRequest){
        doctor.setFirstName(doctorRequest.getFirstName());
        doctor.setLastName(doctorRequest.getLastName());
        doctor.setSpecialization(doctorRequest.getSpecialization());
        doctor.setExperience(doctorRequest.getExperience());
        doctor.setIdentityDocuments(StreamUtils.emptyIfNull(doctorRequest.getDocuments()).map(commonTransformer::convertRequestToModel).toList());
        doctor.setAssociatedClinics(StreamUtils.emptyIfNull(doctorRequest.getAssociatedClinics()).map(commonTransformer::convertRequestToModel).toList());
        doctor.setAddress(ObjectUtils.transformIfNotNull(doctorRequest.getAddress(), commonTransformer::convertRequestToModel));
        doctor.setServedOrganizations(StreamUtils.emptyIfNull(doctorRequest.getServedOrganizations()).map(commonTransformer::convertRequestToModel).toList());
    }

    public <T> SignupInput convertRequestToSignupInput(DoctorRequest<T> doctorRequest) {
        return switch (doctorRequest.getSignupUserInfo()) {
            case UserEmailRequest userEmailRequest -> new SignupInput(
                    new EmailInput(userEmailRequest.getEmail()),
                    null,
                    doctorRequest.getPassword()
            );
            case UserMobileRequest userMobileRequest -> new SignupInput(
                    null,
                    new MobileInput(COUNTRY_CODE, userMobileRequest.getMobile()),
                    doctorRequest.getPassword()
            );
            default -> null;
        };
    }
}
