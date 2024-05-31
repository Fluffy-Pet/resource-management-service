package org.fluffy.pet.rms.resourcemanagement.controller;

import jakarta.validation.Valid;
import org.fluffy.pet.rms.resourcemanagement.dto.request.clinic.ClinicRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.clinic.ClinicResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ResponseWrapper;
import org.fluffy.pet.rms.resourcemanagement.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clinics")
public class ClinicController {
    private final ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping("/{clinicId}")
    public ResponseEntity<ResponseWrapper<ClinicResponse>> getClinic(
            @PathVariable("clinicId") String clinicId
    ) {
        ClinicResponse clinicResponse = clinicService.getClinic(clinicId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(clinicResponse));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<ClinicResponse>> createClinic(
            @RequestBody @Valid ClinicRequest createClinicRequest
    ) {
        ClinicResponse clinicResponse = clinicService.createClinic(createClinicRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.success(clinicResponse));
    }

    @PutMapping("/{clinicId}")
    public ResponseEntity<ResponseWrapper<ClinicResponse>> updateInfrastructure(
            @PathVariable("clinicId") String clinicId,
            @RequestBody @Valid ClinicRequest updateClinicRequest
    ) {
        ClinicResponse clinicResponse = clinicService.updateClinic(updateClinicRequest, clinicId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(clinicResponse));
    }

    @DeleteMapping("/{clinicId}")
    public ResponseEntity<ResponseWrapper<Void>> deleteClinic(
            @PathVariable("clinicId") String clinicId
    ) {
        clinicService.deleteClinic(clinicId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(null));
    }
}
