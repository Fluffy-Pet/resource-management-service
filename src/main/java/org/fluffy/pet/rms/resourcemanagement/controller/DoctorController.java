package org.fluffy.pet.rms.resourcemanagement.controller;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import org.fluffy.pet.rms.resourcemanagement.annotations.CheckAccess;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.DoctorRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.doctor.DoctorResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ResponseWrapper;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;
import org.fluffy.pet.rms.resourcemanagement.service.DoctorService;
import org.fluffy.pet.rms.resourcemanagement.util.PaginationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping
    @CheckAccess(values = {UserType.DOCTOR})
    public ResponseEntity<ResponseWrapper<DoctorResponse>> updateDoctor(
            @RequestBody @Valid DoctorRequest updateDoctorRequest
    ) {
        DoctorResponse doctorResponse = doctorService.updateCurrentDoctor(updateDoctorRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(doctorResponse));
    }

    @DeleteMapping("/{doctorId}")
    @CheckAccess(values = {UserType.DOCTOR})
    public ResponseEntity<ResponseWrapper<Void>> deleteDoctor(
            @PathVariable("doctorId") String doctorId
    ) {
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(null));
    }

    @PostMapping("/filter")
    public ResponseEntity<ResponseWrapper<List<JsonNode>>> filterParents(
            @RequestBody @Valid FilterRequest filterRequest
    ) {
        PaginationWrapper<List<JsonNode>> filterParentPaginationWrapper = doctorService.filterDoctors(filterRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        ResponseWrapper.success(
                                filterParentPaginationWrapper.data(),
                                filterParentPaginationWrapper.paginationResponse()
                        )
                );
    }
}
