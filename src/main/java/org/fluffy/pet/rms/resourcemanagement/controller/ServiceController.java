package org.fluffy.pet.rms.resourcemanagement.controller;

import jakarta.validation.Valid;
import org.fluffy.pet.rms.resourcemanagement.dto.request.service.ServiceRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.service.ServiceResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ResponseWrapper;
import org.fluffy.pet.rms.resourcemanagement.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/services")
@RestController
public class ServiceController {
    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ResponseWrapper<ServiceResponse>> getService(@PathVariable("serviceId") String serviceId) {
        ServiceResponse service = serviceService.getService(serviceId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(service));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<ServiceResponse>> createService(
            @RequestBody @Valid ServiceRequest serviceRequest
    ) {
        ServiceResponse service = serviceService.createService(serviceRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(service));
    }
}
