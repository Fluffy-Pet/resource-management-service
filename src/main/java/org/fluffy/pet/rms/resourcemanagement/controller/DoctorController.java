package org.fluffy.pet.rms.resourcemanagement.controller;

import jakarta.validation.Valid;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.DoctorRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.doctor.DoctorResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ResponseWrapper;
import org.fluffy.pet.rms.resourcemanagement.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController{
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<ResponseWrapper<DoctorResponse>> getDoctor(
            @PathVariable("doctorId") String doctorId
    ) {
        DoctorResponse doctorResponse = doctorService.getDoctor(doctorId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(doctorResponse));
    }

    @PutMapping("/{doctorId}")
    public <T> ResponseEntity<ResponseWrapper<DoctorResponse>> updateDoctor(
            @PathVariable("doctorId") String doctorId,
            @RequestBody @Valid DoctorRequest<T> updateDoctorRequest
    ) {
        DoctorResponse doctorResponse = doctorService.updateDoctor(updateDoctorRequest, doctorId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(doctorResponse));
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<ResponseWrapper<Void>> deleteDoctor(
            @PathVariable("doctorId") String doctorId
    ) {
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(null));
    }
}
