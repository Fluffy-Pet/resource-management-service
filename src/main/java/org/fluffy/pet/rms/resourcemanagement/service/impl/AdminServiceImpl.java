package org.fluffy.pet.rms.resourcemanagement.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.fluffy.pet.rms.resourcemanagement.configuration.contexts.UserContext;
import org.fluffy.pet.rms.resourcemanagement.dto.request.admin.AdminRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.admin.AdminResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.exception.AppException;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.helper.FilterHelper;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Admin;
import org.fluffy.pet.rms.resourcemanagement.repository.AdminRepository;
import org.fluffy.pet.rms.resourcemanagement.service.AdminService;
import org.fluffy.pet.rms.resourcemanagement.transformer.AdminTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.PaginationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    private final AdminTransformer adminTransformer;

    private final FilterHelper<AdminResponse> filterHelper;

    private final UserContext userContext;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, AdminTransformer adminTransformer, FilterHelper<AdminResponse> filterHelper, UserContext userContext) {
        this.adminRepository = adminRepository;
        this.adminTransformer = adminTransformer;
        this.filterHelper = filterHelper;
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

    @Override
    public PaginationWrapper<List<JsonNode>> filterAdmins(FilterRequest filterRequest) {
        try {
            return filterHelper.filterEntities(
                    filterRequest,
                    this::convertModelToResponseFromFilterRequest
            );
        } catch (AppException appException) {
            ErrorResponse errorResponse = ErrorResponse.from(ErrorCode.INVALID_FILTER_REQUEST);
            errorResponse.setDetail(appException.getMessage());
            throw new RestException(HttpStatus.BAD_REQUEST, errorResponse);
        }
    }

    private Page<AdminResponse> convertModelToResponseFromFilterRequest(FilterRequest filterRequest) {
        Page<Admin> admins = adminRepository.filterDocuments(
                filterRequest.getFilters(),
                filterRequest.getSort(),
                filterRequest.getPage(),
                filterRequest.getPageSize()
        );
        return admins.map(adminTransformer::convertModelToResponse);
    }
}
