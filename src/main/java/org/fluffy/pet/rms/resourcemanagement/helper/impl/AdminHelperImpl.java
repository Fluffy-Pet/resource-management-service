package org.fluffy.pet.rms.resourcemanagement.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.fluffy.pet.rms.resourcemanagement.annotations.Helper;
import org.fluffy.pet.rms.resourcemanagement.dto.response.admin.AdminResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.helper.AdminHelper;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserIdentity;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Admin;
import org.fluffy.pet.rms.resourcemanagement.repository.AdminRepository;
import org.fluffy.pet.rms.resourcemanagement.transformer.AdminTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.util.Optional;

@Helper
@Slf4j
public class AdminHelperImpl implements AdminHelper {
    private final AdminRepository adminRepository;

    private final AdminTransformer adminTransformer;

    @Autowired
    public AdminHelperImpl(AdminRepository adminRepository, AdminTransformer adminTransformer) {
        this.adminRepository = adminRepository;
        this.adminTransformer = adminTransformer;
    }

    @Override
    public Result<Void, ErrorCode> createUserEntityForIdOnly(String userId) {
        Admin admin = Admin.builder().id(userId).status(Status.ACTIVE).build();
        try {
            Admin createdAdmin = adminRepository.save(admin);
            log.info("Successfully created empty admin with id {}", createdAdmin.getId());
            return Result.success(null);
        } catch (DuplicateKeyException duplicateKeyException) {
            return Result.error(ErrorCode.DUPLICATE_USER);
        }
    }

    @Override
    public boolean checkUserEntityExists(String userEntityId) {
        return adminRepository.exists(userEntityId);
    }

    @Override
    public Result<AdminResponse, ErrorCode> getUserEntityById(String userId) {
        Optional<Admin> optionalAdmin = adminRepository.findById(userId);
        if (optionalAdmin.isEmpty()) {
            return Result.error(ErrorCode.USER_NOT_FOUND);
        }
        return Result.success(adminTransformer.convertModelToResponse(optionalAdmin.get()));
    }

    @Override
    public Result<UserIdentity, ErrorCode> getUserIdentity(String userId) {
        Optional<Admin> optionalAdmin = adminRepository.findById(userId);
        if (optionalAdmin.isEmpty()) {
            return Result.error(ErrorCode.USER_NOT_FOUND);
        }
        return Result.success(adminTransformer.convertModelToIdentity(optionalAdmin.get()));
    }
}
