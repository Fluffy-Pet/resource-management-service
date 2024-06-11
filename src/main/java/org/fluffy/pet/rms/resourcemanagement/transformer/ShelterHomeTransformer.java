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
                .contactInformation(ObjectUtils.transformIfNotNull(shelterHome.getContactInformation(), commonTransformer::convertModelToResponse))
                .adoptionInformation(shelterHome.getAdoptionInformation())
                .build();
    }

    public ShelterHome convertRequestToModel(ShelterHomeRequest shelterHomeRequest) {
        return ShelterHome.builder()
                .name(shelterHomeRequest.getName())
                .address(ObjectUtils.transformIfNotNull(shelterHomeRequest.getAddress(), commonTransformer::convertRequestToModel))
                .acceptedPetTypes(shelterHomeRequest.getAcceptedPetTypes())
                .capacity(shelterHomeRequest.getCapacity())
                .contactInformation(ObjectUtils.transformIfNotNull(shelterHomeRequest.getContactInformation(), commonTransformer::convertRequestToModel))
                .adoptionInformation(shelterHomeRequest.getAdoptionInformation())
                .website(shelterHomeRequest.getWebsite())
                .build();
    }

    public void updateShelterHome(ShelterHome shelterHome, ShelterHomeRequest shelterHomeRequest) {
        shelterHome.setName(shelterHomeRequest.getName());
        shelterHome.setAddress(ObjectUtils.transformIfNotNull(shelterHomeRequest.getAddress(), commonTransformer::convertRequestToModel));
        shelterHome.setAcceptedPetTypes(shelterHomeRequest.getAcceptedPetTypes());
        shelterHome.setCapacity(shelterHomeRequest.getCapacity());
        shelterHome.setContactInformation(ObjectUtils.transformIfNotNull(shelterHomeRequest.getContactInformation(), commonTransformer::convertRequestToModel));
        shelterHome.setAdoptionInformation(shelterHomeRequest.getAdoptionInformation());
        shelterHome.setWebsite(shelterHomeRequest.getWebsite());
    }
}
