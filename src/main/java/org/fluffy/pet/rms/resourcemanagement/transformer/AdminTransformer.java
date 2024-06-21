package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.admin.AdminRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.admin.AdminResponse;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Admin;

@Transformer
public class AdminTransformer {
    public AdminResponse convertModelToResponse(Admin admin) {
        return AdminResponse
                .builder()
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .profileUrl(admin.getProfileImageFileName())
                .build();
    }

    public void updateAdmin(Admin admin, AdminRequest adminRequest) {
        admin.setFirstName(adminRequest.getFirstName());
        admin.setLastName(adminRequest.getLastName());
        admin.setProfileImageFileName(adminRequest.getProfileUrl());
    }
}
