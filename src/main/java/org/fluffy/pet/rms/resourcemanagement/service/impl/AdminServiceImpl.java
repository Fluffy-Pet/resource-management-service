package org.fluffy.pet.rms.resourcemanagement.service.impl;

import org.fluffy.pet.rms.resourcemanagement.configuration.contexts.UserContext;
import org.fluffy.pet.rms.resourcemanagement.dto.request.admin.AdminRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.admin.AdminResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Admin;
import org.fluffy.pet.rms.resourcemanagement.repository.AdminRepository;
import org.fluffy.pet.rms.resourcemanagement.service.AdminService;
import org.fluffy.pet.rms.resourcemanagement.transformer.AdminTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    private final AdminTransformer adminTransformer;

    private final UserContext userContext;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, AdminTransformer adminTransformer, UserContext userContext) {
        this.adminRepository = adminRepository;
        this.adminTransformer = adminTransformer;
        this.userContext = userContext;
    }

    @Override
    public AdminResponse getCurrentAdminUser() {
        Admin admin = adminRepository.findById(userContext.getUserId()).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.ADMIN_NOT_FOUND))
        );
        return adminTransformer.convertModelToResponse(admin);
    }

    @Override
    public AdminResponse updateCurrentAdmin(AdminRequest adminRequest) {
        Admin admin = adminRepository.findById(userContext.getUserId()).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.ADMIN_NOT_FOUND))
        );
        adminTransformer.updateAdmin(admin, adminRequest);
        Admin updatedAdmin = adminRepository.save(admin);
        return adminTransformer.convertModelToResponse(updatedAdmin);
    }
}
