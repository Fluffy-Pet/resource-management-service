package org.fluffy.pet.rms.resourcemanagement.controller;

import jakarta.validation.Valid;
import org.fluffy.pet.rms.resourcemanagement.annotations.CheckAccess;
import org.fluffy.pet.rms.resourcemanagement.dto.request.admin.AdminRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.admin.AdminResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ResponseWrapper;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;
import org.fluffy.pet.rms.resourcemanagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admins")
@RestController
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<AdminResponse>> getCurrentAdminUser() {
        AdminResponse currentAdminUser = adminService.getCurrentAdminUser();
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(currentAdminUser));
    }

    @PutMapping
    @CheckAccess(values = {UserType.ADMIN})
    public ResponseEntity<ResponseWrapper<AdminResponse>> updateCurrentAdmin(
            @RequestBody @Valid AdminRequest adminRequest
    ) {
        AdminResponse currentAdminUser = adminService.updateCurrentAdmin(adminRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(currentAdminUser));
    }
}
