package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.DoctorRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.DocumentResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.doctor.DoctorResponse;
import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;
import org.fluffy.pet.rms.resourcemanagement.model.common.IdentityDocument;
import org.fluffy.pet.rms.resourcemanagement.model.s3.GetFileUrlInput;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Doctor;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Transformer
public class DoctorTransformer {

    private final CommonTransformer commonTransformer;

    @Autowired
    public DoctorTransformer(CommonTransformer commonTransformer) {
        this.commonTransformer = commonTransformer;
    }

    public DocumentResponse convertDocumentToResponse(IdentityDocument identityDocument, String documentUrl) {
        return commonTransformer.convertModelToResponse(identityDocument, documentUrl);
    }

    public DoctorResponse convertModelToResponse(Doctor doctor, List<Clinic> clinics, List<DocumentResponse> documentResponses){
        return DoctorResponse
                .builder()
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .specialization(doctor.getSpecialization())
                .experience(doctor.getExperience())
                .documents(documentResponses)
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
    }

    public GetFileUrlInput convertIdentityDocumentToFileUrlInput(IdentityDocument identityDocument) {
        return commonTransformer.convertModelToFileUrlInput(identityDocument.getDocumentFileName());
    }
}
