package org.fluffy.pet.rms.resourcemanagement.controller;

import jakarta.validation.Valid;
import org.fluffy.pet.rms.resourcemanagement.annotations.CheckAccess;
import org.fluffy.pet.rms.resourcemanagement.dto.request.service.FluffyPetServiceRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.service.FluffyPetServiceResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ResponseWrapper;
import org.fluffy.pet.rms.resourcemanagement.enums.ServiceType;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;
import org.fluffy.pet.rms.resourcemanagement.service.FluffyPetServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/services")
@RestController
public class FluffyPetServiceController {
    private final FluffyPetServiceService fluffyPetServiceService;

    @Autowired
    public FluffyPetServiceController(FluffyPetServiceService fluffyPetServiceService) {
        this.fluffyPetServiceService = fluffyPetServiceService;
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ResponseWrapper<FluffyPetServiceResponse>> getService(@PathVariable("serviceId") String serviceId) {
        FluffyPetServiceResponse fluffyPetServiceResponse = fluffyPetServiceService.getService(serviceId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(fluffyPetServiceResponse));
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<ResponseWrapper<List<FluffyPetServiceResponse>>> getServiceForProvider(@PathVariable("providerId") String providerId) {
        List<FluffyPetServiceResponse> fluffyPetServiceResponseList = fluffyPetServiceService.getServiceForProvider(providerId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(fluffyPetServiceResponseList));
    }

    @GetMapping("/type/{serviceType}")
    public ResponseEntity<ResponseWrapper<List<FluffyPetServiceResponse>>> getServicesForServiceType(
            @PathVariable("serviceType") String serviceType
    ) {
        List<FluffyPetServiceResponse> fluffyPetServiceResponseList = fluffyPetServiceService.getServiceForServiceType(serviceType);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(fluffyPetServiceResponseList));
    }

    @PostMapping
    @CheckAccess(values = {UserType.ADMIN, UserType.DOCTOR, UserType.VOLUNTEER})
    public ResponseEntity<ResponseWrapper<FluffyPetServiceResponse>> createService(
            @RequestBody @Valid FluffyPetServiceRequest fluffyPetServiceRequest
    ) {
        FluffyPetServiceResponse fluffyPetServiceResponse = fluffyPetServiceService.createService(fluffyPetServiceRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.success(fluffyPetServiceResponse));
    }

    @PutMapping("/{serviceId}")
    @CheckAccess(values = {UserType.ADMIN, UserType.DOCTOR, UserType.VOLUNTEER})
    public ResponseEntity<ResponseWrapper<FluffyPetServiceResponse>> updateServices(
            @PathVariable("serviceId") String serviceId,
            @RequestBody @Valid FluffyPetServiceRequest fluffyPetServiceRequest
    ) {
        FluffyPetServiceResponse fluffyPetServiceResponse = fluffyPetServiceService.updateService(fluffyPetServiceRequest, serviceId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.success(fluffyPetServiceResponse));
    }

    @DeleteMapping("/{serviceId}")
    @CheckAccess(values = {UserType.ADMIN, UserType.DOCTOR, UserType.VOLUNTEER})
    public ResponseEntity<ResponseWrapper<Void>> deleteService(@PathVariable("serviceId") String serviceId) {
        fluffyPetServiceService.deleteService(serviceId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(null));
    }
}
