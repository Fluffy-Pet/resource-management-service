package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.shelter.ShelterHomeRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.shelter.ShelterHomeResponse;
import org.fluffy.pet.rms.resourcemanagement.model.shelter.ShelterHome;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

@Transformer
public class ShelterHomeTransformer {
    private  final CommonTransformer commonTransformer;

    @Autowired
    public ShelterHomeTransformer(CommonTransformer commonTransformer) {
        this.commonTransformer = commonTransformer;
    }

    public ShelterHomeResponse convertModelToResponse(ShelterHome shelterHome) {
        return ShelterHomeResponse
                .builder()
                .name(shelterHome.getName())
                .description(shelterHome.getDescription())
                .profileImageUrl(
                        ObjectUtils.transformIfNotNull(
                                ObjectUtils.transformIfNotNull(
                                        shelterHome.getProfileImageFileName(),
                                        commonTransformer::convertFileNameToUrl
                                ),
                                URL::toString
                        )
                )
                .mobile(ObjectUtils.transformIfNotNull(shelterHome.getMobile(), commonTransformer::convertModelToResponse))
                .email(ObjectUtils.transformIfNotNull(shelterHome.getEmail(), commonTransformer::convertModelToResponse))
                .capacity(shelterHome.getCapacity())
                .address(ObjectUtils.transformIfNotNull(shelterHome.getAddress(), commonTransformer::convertModelToResponse))
                .acceptedPetTypes(shelterHome.getAcceptedPetTypes())
                .build();
    }

    public ShelterHome convertRequestToModel(ShelterHomeRequest shelterHomeRequest) {
        return ShelterHome
                .builder()
                .name(shelterHomeRequest.getName())
                .description(shelterHomeRequest.getDescription())
                .profileImageFileName(shelterHomeRequest.getProfileImageFileName())
                .capacity(shelterHomeRequest.getCapacity())
                .mobile(ObjectUtils.transformIfNotNull(shelterHomeRequest.getMobile(), commonTransformer::convertMobileRequestToModel))
                .email(ObjectUtils.transformIfNotNull(shelterHomeRequest.getEmail(), commonTransformer::convertEmailRequestToModel))
                .address(ObjectUtils.transformIfNotNull(shelterHomeRequest.getAddress(), commonTransformer::convertRequestToModel))
                .acceptedPetTypes(shelterHomeRequest.getAcceptedPetTypes())
                .build();
    }

    public void updateShelterHome(ShelterHome shelterHome, ShelterHomeRequest shelterHomeRequest) {
        shelterHome.setName(shelterHomeRequest.getName());
        shelterHome.setDescription(shelterHomeRequest.getDescription());
        shelterHome.setProfileImageFileName(shelterHomeRequest.getProfileImageFileName());
        shelterHome.setCapacity(shelterHomeRequest.getCapacity());
        shelterHome.setMobile(ObjectUtils.transformIfNotNull(shelterHomeRequest.getMobile(), commonTransformer::convertMobileRequestToModel));
        shelterHome.setEmail(ObjectUtils.transformIfNotNull(shelterHomeRequest.getEmail(), commonTransformer::convertEmailRequestToModel));
        shelterHome.setAddress(ObjectUtils.transformIfNotNull(shelterHomeRequest.getAddress(), commonTransformer::convertRequestToModel));
        shelterHome.setAcceptedPetTypes(shelterHomeRequest.getAcceptedPetTypes());
    }
}
