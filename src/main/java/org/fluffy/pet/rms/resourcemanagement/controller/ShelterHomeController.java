package org.fluffy.pet.rms.resourcemanagement.controller;

import jakarta.validation.Valid;
import org.fluffy.pet.rms.resourcemanagement.dto.request.shelter.ShelterHomeRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.shelter.ShelterHomeResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ResponseWrapper;
import org.fluffy.pet.rms.resourcemanagement.service.ShelterHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shelterhomes")
public class ShelterHomeController {
    private final ShelterHomeService shelterHomeService;

    @Autowired
    public ShelterHomeController(ShelterHomeService shelterHomeService) {
        this.shelterHomeService = shelterHomeService;
    }

    @GetMapping("/{shelterHomeId}")
    public ResponseEntity<ResponseWrapper<ShelterHomeResponse>> getShelterHome(
            @PathVariable("shelterHomeId") String ShelterHomeId
    ) {
        ShelterHomeResponse shelterHomeResponse = shelterHomeService.getShelterHome(ShelterHomeId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(shelterHomeResponse));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<ShelterHomeResponse>> createShelterHome(
            @RequestBody @Valid ShelterHomeRequest createShelterHomeRequest
    ) {
        ShelterHomeResponse shelterHomeResponse = shelterHomeService.createShelterHome(createShelterHomeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.success(shelterHomeResponse));
    }

    @PutMapping("/{shelterHomeId}")
    public ResponseEntity<ResponseWrapper<ShelterHomeResponse>> updateShelterHome(
            @PathVariable("shelterHomeId") String shelterHomeId,
            @RequestBody @Valid ShelterHomeRequest updateShelterHomeRequest
    ) {
        ShelterHomeResponse shelterHomeResponse = shelterHomeService.updateShelterHome(updateShelterHomeRequest, shelterHomeId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(shelterHomeResponse));
    }

    @DeleteMapping("/{shelterHomeId}")
    public ResponseEntity<ResponseWrapper<Void>> deleteShelterHome(
            @PathVariable("shelterHomeId") String shelterHomeId
    ) {
        shelterHomeService.deleteShelterHome(shelterHomeId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(null));
    }
}

