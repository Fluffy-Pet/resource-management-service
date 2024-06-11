package org.fluffy.pet.rms.resourcemanagement.controller;

import jakarta.validation.Valid;
import org.fluffy.pet.rms.resourcemanagement.dto.request.pet.PetRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.pet.PetResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ResponseWrapper;
import org.fluffy.pet.rms.resourcemanagement.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pets")
@RestController
public class PetController {
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/owned/current-user")
    public ResponseEntity<ResponseWrapper<List<PetResponse>>> getOwnedPetsForCurrentUser() {
        List<PetResponse> petResponse = petService.getOwnedPetsForCurrentUser();
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(petResponse));
    }

    @GetMapping("/{petId}")
    public ResponseEntity<ResponseWrapper<PetResponse>> getPet(@PathVariable("petId") String petId) {
        PetResponse petResponse = petService.getPetById(petId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(petResponse));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<PetResponse>> createPet(
            @RequestBody @Valid PetRequest petRequest
    ) {
        PetResponse petResponse = petService.createPet(petRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(petResponse));
    }

    @PutMapping("/{petId}")
    public ResponseEntity<ResponseWrapper<PetResponse>> updatePet(
            @PathVariable("petId") String petId,
            @RequestBody @Valid PetRequest petRequest
    ) {
        PetResponse petResponse = petService.updatePet(petRequest, petId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(petResponse));
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<ResponseWrapper<Void>> deletePet(@PathVariable("petId") String petId) {
        petService.deletePet(petId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(null));
    }
}
