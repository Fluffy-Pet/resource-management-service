package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.DoctorRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.doctor.DoctorResponse;
import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;
import org.fluffy.pet.rms.resourcemanagement.model.common.ProviderIdentity;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserIdentity;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Doctor;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;


@Transformer
public class DoctorTransformer {
    private final CommonTransformer commonTransformer;

    @Autowired
    public DoctorTransformer(CommonTransformer commonTransformer) {
        this.commonTransformer = commonTransformer;
    }

    public DoctorResponse convertModelToResponse(Doctor doctor, List<Clinic> clinics){
        return DoctorResponse
                .builder()
                .id(doctor.getId())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .profileImageUrl(ObjectUtils.transformIfNotNull(
                        ObjectUtils.transformIfNotNull(doctor.getProfileImageFileName(), commonTransformer::convertFileNameToUrl),
                        URL::toString
                ))
                .specialization(doctor.getSpecialization())
                .experience(doctor.getExperience())
                .documents(StreamUtils.emptyIfNull(doctor.getDocuments(), true).map(commonTransformer::convertModelToResponse).toList())
                .associatedClinics(StreamUtils.emptyIfNull(clinics).map(commonTransformer::convertModelToResponse).toList())
                .address(ObjectUtils.transformIfNotNull(doctor.getAddress(), commonTransformer::convertModelToResponse))
                .servedOrganizations(StreamUtils.emptyIfNull(doctor.getServedOrganizations()).map(commonTransformer::convertModelToResponse).toList())
                .build();
    }

    public void updateDoctor(Doctor doctor, DoctorRequest doctorRequest){
        doctor.setFirstName(doctorRequest.getFirstName());
        doctor.setLastName(doctorRequest.getLastName());
        doctor.setSpecialization(doctorRequest.getSpecialization());
        doctor.setExperience(doctorRequest.getExperience());
        doctor.setDocuments(StreamUtils.emptyIfNull(doctorRequest.getDocuments()).map(commonTransformer::convertRequestToModel).toList());
        doctor.setAssociatedClinics(StreamUtils.emptyIfNull(doctorRequest.getAssociatedClinics()).map(commonTransformer::convertRequestToModel).toList());
        doctor.setAddress(ObjectUtils.transformIfNotNull(doctorRequest.getAddress(), commonTransformer::convertRequestToModel));
        doctor.setServedOrganizations(StreamUtils.emptyIfNull(doctorRequest.getServedOrganizations()).map(commonTransformer::convertRequestToModel).toList());
        doctor.setProfileImageFileName(doctorRequest.getProfileImageFileName());
    }

    public UserIdentity convertModelToIdentity(Doctor doctor) {
        return UserIdentity
                .builder()
                .userId(doctor.getId())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .profilePhotoFileName(
                        ObjectUtils.transformIfNotNull(
                                ObjectUtils.transformIfNotNull(
                                        doctor.getProfileImageFileName(),
                                        commonTransformer::convertFileNameToUrl
                                ),
                                URL::toString
                        )
                )
                .build();
    }

    public ProviderIdentity convertModelToProviderIdentity(Doctor doctor) {
        return ProviderIdentity
                .builder()
                .providerId(doctor.getId())
                .name(doctor.getFirstName())
                .profileImageFileName(
                        ObjectUtils.transformIfNotNull(
                                ObjectUtils.transformIfNotNull(
                                        doctor.getProfileImageFileName(),
                                        commonTransformer::convertFileNameToUrl
                                ),
                                URL::toString
                        )
                )
                .build();
    }
}
