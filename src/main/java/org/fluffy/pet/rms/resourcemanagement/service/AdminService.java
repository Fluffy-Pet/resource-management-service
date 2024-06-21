package org.fluffy.pet.rms.resourcemanagement.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.fluffy.pet.rms.resourcemanagement.dto.request.admin.AdminRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.admin.AdminResponse;
import org.fluffy.pet.rms.resourcemanagement.util.PaginationWrapper;

import java.util.List;

public interface AdminService {
    AdminResponse getCurrentAdminUser();

    AdminResponse updateCurrentAdmin(AdminRequest adminRequest);

    PaginationWrapper<List<JsonNode>> filterAdmins(FilterRequest filterRequest);
}
