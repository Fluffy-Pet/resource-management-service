package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.shelter.ShelterHomeRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.shelter.ShelterHomeResponse;
import org.fluffy.pet.rms.resourcemanagement.model.common.ProviderIdentity;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserIdentity;
import org.fluffy.pet.rms.resourcemanagement.model.shelter.ShelterHome;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.UUID;

@Transformer
public class ShelterHomeTransformer {
    private  final CommonTransformer commonTransformer;

    @Autowired
    public ShelterHomeTransformer(CommonTransformer commonTransformer) {
        this.commonTransformer = commonTransformer;
    }

    public ShelterHomeResponse convertModelToResponse(ShelterHome shelterHome, UserIdentity userIdentity) {
        return ShelterHomeResponse
                .builder()
                .id(shelterHome.getId())
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
                .address(ObjectUtils.transformIfNotNull(shelterHome.getAddress(), commonTransformer::convertModelToResponse))
                .owner(commonTransformer.convertModelToIdentity(userIdentity))
                .build();
    }

    public ShelterHome convertRequestToModel(ShelterHomeRequest shelterHomeRequest, String userId) {
        return ShelterHome
                .builder()
                .id(UUID.randomUUID().toString())
                .name(shelterHomeRequest.getName())
                .description(shelterHomeRequest.getDescription())
                .profileImageFileName(shelterHomeRequest.getProfileImageFileName())
                .address(ObjectUtils.transformIfNotNull(shelterHomeRequest.getAddress(), commonTransformer::convertRequestToModel))
                .owner(commonTransformer.convertUserIdToOwner(userId))
                .build();
    }

    public void updateShelterHome(ShelterHome shelterHome, ShelterHomeRequest shelterHomeRequest) {
        shelterHome.setName(shelterHomeRequest.getName());
        shelterHome.setDescription(shelterHomeRequest.getDescription());
        shelterHome.setProfileImageFileName(shelterHomeRequest.getProfileImageFileName());
        shelterHome.setAddress(ObjectUtils.transformIfNotNull(shelterHomeRequest.getAddress(), commonTransformer::convertRequestToModel));
    }

    public ProviderIdentity convertModelToIdentity(ShelterHome shelterHome) {
        return ProviderIdentity
                .builder()
                .providerId(shelterHome.getId())
                .name(shelterHome.getName())
                .profileImageFileName(
                        ObjectUtils.transformIfNotNull(
                                ObjectUtils.transformIfNotNull(
                                        shelterHome.getProfileImageFileName(),
                                        commonTransformer::convertFileNameToUrl
                                ),
                                URL::toString
                        )
                )
                .build();
    }
}
