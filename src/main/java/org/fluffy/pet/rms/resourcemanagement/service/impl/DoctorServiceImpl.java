package org.fluffy.pet.rms.resourcemanagement.service.impl;

import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import lombok.extern.slf4j.Slf4j;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.DoctorRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.doctor.DoctorResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Doctor;
import org.fluffy.pet.rms.resourcemanagement.repository.DoctorRepository;
import org.fluffy.pet.rms.resourcemanagement.service.DoctorService;
import org.fluffy.pet.rms.resourcemanagement.transformer.DoctorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    private final DoctorTransformer doctorTransformer;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorTransformer doctorTransformer){
        this.doctorRepository = doctorRepository;
        this.doctorTransformer = doctorTransformer;
    }


    @Override
    public DoctorResponse createDoctor(DoctorRequest doctorRequest) {
        Doctor doctor = doctorTransformer.convertRequestToModel(doctorRequest);
        try {
            doctorRepository.save(doctor);
            return doctorTransformer.convertModelToResponse(doctor);
        } catch (ConditionalCheckFailedException e) {
            log.error(
                    String.format("Exception happened in creating user for %s", doctorRequest.getFirstName()), e
            );
            throw new RestException(HttpStatus.BAD_REQUEST, ErrorResponse.from(ErrorCode.DOCTOR_ALREADY_EXISTS));
        }
    }

    @Override
    public DoctorResponse getDoctor(String id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.DOCTOR_NOT_FOUND))
        );
        return doctorTransformer.convertModelToResponse(doctor);
    }

    @Override
    public DoctorResponse updateDoctor(DoctorRequest updateDoctorRequest, String id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.DOCTOR_NOT_FOUND))
        );
        doctorTransformer.updateDoctor(doctor, updateDoctorRequest);
        doctorRepository.save(doctor);
        return doctorTransformer.convertModelToResponse(doctor);
    }

    @Override
    public void deleteDoctor(String id) {
        doctorRepository.deleteById(id);
    }
}
