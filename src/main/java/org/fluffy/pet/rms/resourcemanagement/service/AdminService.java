package org.fluffy.pet.rms.resourcemanagement.service;

import org.fluffy.pet.rms.resourcemanagement.dto.request.admin.AdminRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.admin.AdminResponse;

public interface AdminService {
    AdminResponse getCurrentAdminUser();

    AdminResponse updateCurrentAdmin(AdminRequest adminRequest);
}
