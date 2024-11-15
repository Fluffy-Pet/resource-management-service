package org.fluffy.pet.rms.resourcemanagement.controller;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import org.fluffy.pet.rms.resourcemanagement.annotations.CheckAccess;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.volunteer.VolunteerRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.volunteer.VolunteerResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ResponseWrapper;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;
import org.fluffy.pet.rms.resourcemanagement.service.VolunteerService;
import org.fluffy.pet.rms.resourcemanagement.util.PaginationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping
    @CheckAccess(values = {UserType.VOLUNTEER})
    public ResponseEntity<ResponseWrapper<VolunteerResponse>> updateVolunteer(
            @RequestBody @Valid VolunteerRequest updateVolunteerRequest
    ) {
        VolunteerResponse volunteerResponse = volunteerService.updateCurrentVolunteer(updateVolunteerRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(volunteerResponse));
    }

    @DeleteMapping("/{volunteerId}")
    @CheckAccess(values = {UserType.VOLUNTEER})
    public ResponseEntity<ResponseWrapper<Void>> deleteVolunteer(
            @PathVariable("volunteerId") String volunteerId
    ) {
        volunteerService.deleteVolunteer(volunteerId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(null));
    }

    @PostMapping("/filter")
    public ResponseEntity<ResponseWrapper<List<JsonNode>>> filterVolunteers(
            @RequestBody @Valid FilterRequest filterRequest
    ) {
        PaginationWrapper<List<JsonNode>> filterParentPaginationWrapper = volunteerService.filterVolunteers(filterRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        ResponseWrapper.success(
                                filterParentPaginationWrapper.data(),
                                filterParentPaginationWrapper.paginationResponse()
                        )
                );
    }
}
