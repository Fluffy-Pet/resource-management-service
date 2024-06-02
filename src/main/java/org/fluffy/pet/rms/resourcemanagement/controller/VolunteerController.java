package org.fluffy.pet.rms.resourcemanagement.controller;

import jakarta.validation.Valid;
import org.fluffy.pet.rms.resourcemanagement.dto.request.volunteer.VolunteerRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.volunteer.VoluteerResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ResponseWrapper;
import org.fluffy.pet.rms.resourcemanagement.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/volunteers")
public class VolunteerController{
    private final VolunteerService volunteerService;

    @Autowired
    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping("/{volunteerId}")
    public ResponseEntity<ResponseWrapper<VoluteerResponse>> getVolunteer(
            @PathVariable("volunteerId") String volunteerId
    ) {
        VoluteerResponse voluteerResponse = volunteerService.getVolunteer(volunteerId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(voluteerResponse));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<VoluteerResponse>> createVolunteer(
            @RequestBody @Valid VolunteerRequest createVolunteerRequest
    ) {
        VoluteerResponse voluteerResponse = volunteerService.createVolunteer(createVolunteerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.success(voluteerResponse));
    }

    @PutMapping("/{volunteerId}")
    public ResponseEntity<ResponseWrapper<VoluteerResponse>> updateVolunteer(
            @PathVariable("volunteerId") String volunteerId,
            @RequestBody @Valid VolunteerRequest updateVolunteerRequest
    ) {
        VoluteerResponse voluteerResponse = volunteerService.updateVolunteer(updateVolunteerRequest, volunteerId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(voluteerResponse));
    }

    @DeleteMapping("/{volunteerId}")
    public ResponseEntity<ResponseWrapper<Void>> deleteVolunteer(
            @PathVariable("volunteerId") String volunteerId
    ) {
        volunteerService.deleteVolunteer(volunteerId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(null));
    }
}
