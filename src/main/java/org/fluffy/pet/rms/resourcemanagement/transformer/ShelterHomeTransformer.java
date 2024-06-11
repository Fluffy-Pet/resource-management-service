package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.shelter.ShelterHomeRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.shelter.ShelterHomeResponse;
import org.fluffy.pet.rms.resourcemanagement.model.shelter.ShelterHome;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
                .address(ObjectUtils.transformIfNotNull(shelterHome.getAddress(), commonTransformer::convertModelToResponse))
                .acceptedPetTypes(shelterHome.getAcceptedPetTypes())
                .capacity(shelterHome.getCapacity())
                .email(ObjectUtils.transformIfNotNull(shelterHome.getEmail(), commonTransformer::convertModelToResponse))
                .mobile(ObjectUtils.transformIfNotNull(shelterHome.getMobile(), commonTransformer::convertModelToResponse))
                .build();
    }

    public ShelterHome convertRequestToModel(ShelterHomeRequest shelterHomeRequest) {
        return ShelterHome.builder()
                .name(shelterHomeRequest.getName())
                .address(ObjectUtils.transformIfNotNull(shelterHomeRequest.getAddress(), commonTransformer::convertRequestToModel))
                .acceptedPetTypes(shelterHomeRequest.getAcceptedPetTypes())
                .capacity(shelterHomeRequest.getCapacity())
                .email(ObjectUtils.transformIfNotNull(shelterHomeRequest.getEmail(), commonTransformer::convertEmailRequestToModel))
                .mobile(ObjectUtils.transformIfNotNull(shelterHomeRequest.getMobile(), commonTransformer::convertMobileRequestToModel))
                .website(shelterHomeRequest.getWebsite())
                .build();
    }

    public void updateShelterHome(ShelterHome shelterHome, ShelterHomeRequest shelterHomeRequest) {
        shelterHome.setName(shelterHomeRequest.getName());
        shelterHome.setAddress(ObjectUtils.transformIfNotNull(shelterHomeRequest.getAddress(), commonTransformer::convertRequestToModel));
        shelterHome.setAcceptedPetTypes(shelterHomeRequest.getAcceptedPetTypes());
        shelterHome.setCapacity(shelterHomeRequest.getCapacity());
        shelterHome.setEmail(ObjectUtils.transformIfNotNull(shelterHomeRequest.getEmail(), commonTransformer::convertEmailRequestToModel));
        shelterHome.setMobile(ObjectUtils.transformIfNotNull(shelterHomeRequest.getMobile(), commonTransformer::convertMobileRequestToModel));
        shelterHome.setWebsite(shelterHomeRequest.getWebsite());
    }
}
