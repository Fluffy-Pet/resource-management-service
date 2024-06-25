package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.clinic.ClinicRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.clinic.ClinicResponse;
import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Transformer
public class ClinicTransformer {
    private final CommonTransformer commonTransformer;

    @Autowired
    public ClinicTransformer(CommonTransformer commonTransformer) {
        this.commonTransformer = commonTransformer;
    }

    public Clinic convertRequestToModel(ClinicRequest clinicRequest){
        return Clinic
                .builder()
                .id(UUID.randomUUID().toString())
                .clinicName(clinicRequest.getClinicName())
                .description(clinicRequest.getDescription())
                .address(ObjectUtils.transformIfNotNull(clinicRequest.getAddress(), commonTransformer::convertRequestToModel))
                .operatingHours(ObjectUtils.transformIfNotNull(clinicRequest.getOperatingHours(), commonTransformer::convertRequestToModel))
                .servicesOffered(StreamUtils.emptyIfNull(clinicRequest.getServicesOffered()).map(commonTransformer::convertRequestToModel).toList())
                .build();
    }

    public ClinicResponse convertModelToResponse(Clinic clinic){
        return ClinicResponse
                .builder()
                .clinicName(clinic.getClinicName())
                .address(ObjectUtils.transformIfNotNull(clinic.getAddress(), commonTransformer::convertModelToResponse))
                .openingHours(ObjectUtils.transformIfNotNull(clinic.getOperatingHours(), commonTransformer::convertModelToResponse))
                .servicesOffered(StreamUtils.emptyIfNull(clinic.getServicesOffered()).map(commonTransformer::convertModelToResponse).toList())
                .build();
    }

    public void updateClinic(Clinic clinic, ClinicRequest clinicRequest){
        clinic.setClinicName(clinicRequest.getClinicName());
        clinic.setDescription(clinicRequest.getDescription());
        clinic.setAddress(ObjectUtils.transformIfNotNull(clinicRequest.getAddress(), commonTransformer::convertRequestToModel));
        clinic.setOperatingHours(ObjectUtils.transformIfNotNull(clinicRequest.getOperatingHours(), commonTransformer::convertRequestToModel));
        clinic.setServicesOffered(StreamUtils.emptyIfNull(clinicRequest.getServicesOffered()).map(commonTransformer::convertRequestToModel).toList());
    }
}
