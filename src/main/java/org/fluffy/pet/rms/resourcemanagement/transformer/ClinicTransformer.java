package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.clinic.ClinicRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.clinic.ClinicResponse;
import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;
import org.fluffy.pet.rms.resourcemanagement.model.common.ProviderIdentity;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserIdentity;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.UUID;

@Transformer
public class ClinicTransformer {
    private final CommonTransformer commonTransformer;

    @Autowired
    public ClinicTransformer(CommonTransformer commonTransformer) {
        this.commonTransformer = commonTransformer;
    }

    public Clinic convertRequestToModel(ClinicRequest clinicRequest, String userId){
        return Clinic
                .builder()
                .id(UUID.randomUUID().toString())
                .clinicName(clinicRequest.getClinicName())
                .description(clinicRequest.getDescription())
                .profileImageFileName(clinicRequest.getProfileImageFileName())
                .address(ObjectUtils.transformIfNotNull(clinicRequest.getAddress(), commonTransformer::convertRequestToModel))
                .operatingHours(ObjectUtils.transformIfNotNull(clinicRequest.getOperatingHours(), commonTransformer::convertRequestToModel))
                .owner(commonTransformer.convertUserIdToOwner(userId))
                .build();
    }

    public ClinicResponse convertModelToResponse(Clinic clinic, UserIdentity userIdentity){
        return ClinicResponse
                .builder()
                .id(clinic.getId())
                .clinicName(clinic.getClinicName())
                .description(clinic.getDescription())
                .profileImageUrl(
                        ObjectUtils.transformIfNotNull(
                                ObjectUtils.transformIfNotNull(
                                        clinic.getProfileImageFileName(),
                                        commonTransformer::convertFileNameToUrl
                                ),
                                URL::toString
                        )
                )
                .address(ObjectUtils.transformIfNotNull(clinic.getAddress(), commonTransformer::convertModelToResponse))
                .openingHours(ObjectUtils.transformIfNotNull(clinic.getOperatingHours(), commonTransformer::convertModelToResponse))
                .owner(commonTransformer.convertModelToIdentity(userIdentity))
                .build();
    }

    public void updateClinic(Clinic clinic, ClinicRequest clinicRequest){
        clinic.setClinicName(clinicRequest.getClinicName());
        clinic.setDescription(clinicRequest.getDescription());
        clinic.setAddress(ObjectUtils.transformIfNotNull(clinicRequest.getAddress(), commonTransformer::convertRequestToModel));
        clinic.setOperatingHours(ObjectUtils.transformIfNotNull(clinicRequest.getOperatingHours(), commonTransformer::convertRequestToModel));
    }

    public ProviderIdentity convertModelToIdentity(Clinic clinic) {
        return ProviderIdentity
                .builder()
                .providerId(clinic.getId())
                .name(clinic.getClinicName())
                .profileImageFileName(
                        ObjectUtils.transformIfNotNull(
                                ObjectUtils.transformIfNotNull(
                                        clinic.getProfileImageFileName(),
                                        commonTransformer::convertFileNameToUrl
                                ),
                                URL::toString
                        )
                )
                .build();
    }
}
