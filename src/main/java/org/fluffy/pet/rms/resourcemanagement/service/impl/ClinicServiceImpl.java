package org.fluffy.pet.rms.resourcemanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.fluffy.pet.rms.resourcemanagement.dto.request.clinic.ClinicRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.clinic.ClinicResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;
import org.fluffy.pet.rms.resourcemanagement.repository.ClinicRepository;
import org.fluffy.pet.rms.resourcemanagement.service.ClinicService;
import org.fluffy.pet.rms.resourcemanagement.transformer.ClinicTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClinicServiceImpl implements ClinicService {

    private final ClinicRepository clinicRepository;

    private final ClinicTransformer clinicTransformer;

    @Autowired
    public ClinicServiceImpl(ClinicRepository clinicRepository, ClinicTransformer clinicTransformer){
        this.clinicRepository = clinicRepository;
        this.clinicTransformer = clinicTransformer;
    }

    @Override
    public ClinicResponse createClinic(ClinicRequest clinicRequest) {
        Clinic clinic = clinicTransformer.convertRequestToModel(clinicRequest);
        try {
            Clinic createdClinic = clinicRepository.save(clinic);
            return clinicTransformer.convertModelToResponse(createdClinic);
        } catch (DuplicateKeyException e) {
            log.error(String.format("Exception happened in creating user for %s", clinicRequest.getName()), e
            );
            throw new RestException(HttpStatus.BAD_REQUEST, ErrorResponse.from(ErrorCode.CLINIC_ALREADY_EXISTS));
        }
    }

    @Override
    public ClinicResponse getClinic(String id) {
        Clinic clinic = clinicRepository.findById(id).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.CLINIC_NOT_FOUND))
        );
        return clinicTransformer.convertModelToResponse(clinic);
    }

    @Override
    public ClinicResponse updateClinic(ClinicRequest updateClinicRequest, String id) {
        Clinic clinic = clinicRepository.findById(id).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.CLINIC_NOT_FOUND))
        );
        clinicTransformer.updateClinic(clinic, updateClinicRequest);
        Clinic updatedClinic = clinicRepository.save(clinic);
        return clinicTransformer.convertModelToResponse(updatedClinic);
    }

    @Override
    public void deleteClinic(String id) {
        clinicRepository.deleteById(id);

    }
}
