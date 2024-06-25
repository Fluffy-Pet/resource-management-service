package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.admin.AdminRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.admin.AdminResponse;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserIdentity;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Admin;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

@Transformer
public class AdminTransformer {
    private final CommonTransformer commonTransformer;

    @Autowired
    public AdminTransformer(CommonTransformer commonTransformer) {
        this.commonTransformer = commonTransformer;
    }

    public AdminResponse convertModelToResponse(Admin admin) {
        return AdminResponse
                .builder()
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .profileUrl(
                        ObjectUtils.transformIfNotNull(
                                ObjectUtils.transformIfNotNull(
                                        admin.getProfileImageFileName(),
                                        commonTransformer::convertFileNameToUrl
                                ),
                                URL::toString
                        )
                )
                .build();
    }

    public void updateAdmin(Admin admin, AdminRequest adminRequest) {
        admin.setFirstName(adminRequest.getFirstName());
        admin.setLastName(adminRequest.getLastName());
        admin.setProfileImageFileName(adminRequest.getProfileImageFileName());
    }

    public UserIdentity convertModelToIdentity(Admin admin) {
        return UserIdentity
                .builder()
                .userId(admin.getId())
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .profilePhotoFileName(
                        ObjectUtils.transformIfNotNull(
                                ObjectUtils.transformIfNotNull(
                                        admin.getProfileImageFileName(),
                                        commonTransformer::convertFileNameToUrl
                                ),
                                URL::toString
                        )
                )
                .build();
    }
}
