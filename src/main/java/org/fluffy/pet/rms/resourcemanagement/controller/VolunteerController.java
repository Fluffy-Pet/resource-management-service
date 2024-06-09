package org.fluffy.pet.rms.resourcemanagement.controller;

import jakarta.validation.Valid;
import org.fluffy.pet.rms.resourcemanagement.dto.request.volunteer.VolunteerRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.volunteer.VolunteerResponse;
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
    public ResponseEntity<ResponseWrapper<VolunteerResponse>> getVolunteer(
            @PathVariable("volunteerId") String volunteerId
    ) {
        VolunteerResponse volunteerResponse = volunteerService.getVolunteer(volunteerId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(volunteerResponse));
    }

    @PostMapping
    public <T> ResponseEntity<ResponseWrapper<VolunteerResponse>> createVolunteer(
            @RequestBody @Valid VolunteerRequest<T> createVolunteerRequest
    ) {
        VolunteerResponse volunteerResponse = volunteerService.createVolunteer(createVolunteerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.success(volunteerResponse));
    }

    @PutMapping("/{volunteerId}")
    public <T> ResponseEntity<ResponseWrapper<VolunteerResponse>> updateVolunteer(
            @PathVariable("volunteerId") String volunteerId,
            @RequestBody @Valid VolunteerRequest<T> updateVolunteerRequest
    ) {
        VolunteerResponse volunteerResponse = volunteerService.updateVolunteer(updateVolunteerRequest, volunteerId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(volunteerResponse));
    }

    @DeleteMapping("/{volunteerId}")
    public ResponseEntity<ResponseWrapper<Void>> deleteVolunteer(
            @PathVariable("volunteerId") String volunteerId
    ) {
        volunteerService.deleteVolunteer(volunteerId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(null));
    }
}
