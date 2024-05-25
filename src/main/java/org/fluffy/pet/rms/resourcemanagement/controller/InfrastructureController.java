package org.fluffy.pet.rms.resourcemanagement.controller;

import jakarta.validation.Valid;
import org.fluffy.pet.rms.resourcemanagement.dto.request.infrastructure.InfrastructureRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.infrastructure.InfrastructureResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ResponseWrapper;
import org.fluffy.pet.rms.resourcemanagement.service.InfrastructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/infrastructure")
public class InfrastructureController {
    private final InfrastructureService infrastructureService;

    @Autowired
    public InfrastructureController(InfrastructureService infrastructureService) {
        this.infrastructureService = infrastructureService;
    }

    @GetMapping("/{infrastructureId}")
    public ResponseEntity<ResponseWrapper<InfrastructureResponse>> getInfrastructure(
            @PathVariable("infrastructureId") String infrastructureId
    ) {
        InfrastructureResponse infrastructureResponse = infrastructureService.getInfrastructure(infrastructureId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(infrastructureResponse));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<InfrastructureResponse>> createInfrastructure(
            @RequestBody @Valid InfrastructureRequest createInfrastructureRequest
    ) {
        InfrastructureResponse infrastructureResponse = infrastructureService.createInfrastructure(createInfrastructureRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.success(infrastructureResponse));
    }

    @PutMapping("/{infrastructureId}")
    public ResponseEntity<ResponseWrapper<InfrastructureResponse>> updateInfrastructure(
            @PathVariable("infrastructureId") String infrastructureId,
            @RequestBody @Valid InfrastructureRequest updateInfrastructureRequest
    ) {
        InfrastructureResponse infrastructure = infrastructureService.updateInfrastructure(updateInfrastructureRequest, infrastructureId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(infrastructure));
    }

    @DeleteMapping("/{infrastructureId}")
    public ResponseEntity<ResponseWrapper<Void>> deleteInfrastructure(
            @PathVariable("infrastructureId") String infrastructureId
    ) {
        infrastructureService.deleteInfrastructure(infrastructureId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(null));
    }
}
