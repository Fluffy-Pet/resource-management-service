package org.fluffy.pet.rms.resourcemanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignInEmailPassword;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignInMobilePassword;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignupInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignInOutput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignupOutput;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.DoctorRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.doctor.DoctorResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.helper.UserHelper;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Doctor;
import org.fluffy.pet.rms.resourcemanagement.repository.DoctorRepository;
import org.fluffy.pet.rms.resourcemanagement.service.DoctorService;
import org.fluffy.pet.rms.resourcemanagement.transformer.DoctorTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode.DUPLICATE_USER;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    private final DoctorTransformer doctorTransformer;

    private final UserHelper userHelper;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorTransformer doctorTransformer,UserHelper userHelper){
        this.doctorRepository = doctorRepository;
        this.doctorTransformer = doctorTransformer;
        this.userHelper= userHelper;
    }

    @Override
    public DoctorResponse createDoctor(DoctorRequest doctorRequest) {
        Doctor doctor = doctorTransformer.convertRequestToModel(doctorRequest);
        SignupInput signupInput=doctorTransformer.convertRequestToSignupInput(doctorRequest);
        Result<SignupOutput, ErrorCode> result = userHelper.signup(signupInput);
        if (result.isSuccess()) {
            doctor.setId(result.getData().userId());
        } else if(result.getError().equals(DUPLICATE_USER)) {
            if (doctorRequest.getEmail() != null) {
            SignInEmailPassword signInEmailPassword = doctorTransformer.convertRequestToSignInEmailPassword(doctorRequest);
            Result<SignInOutput, ErrorCode> signInResult = userHelper.signIn(signInEmailPassword);
            doctor.setId(signInResult.getData().userId());
            } else if (doctorRequest.getMobile() != null) {
            SignInMobilePassword signInMobilePassword = doctorTransformer.convertRequestToSignInMobilePassword(doctorRequest);
            Result<SignInOutput, ErrorCode> signInResult = userHelper.signIn(signInMobilePassword);
            doctor.setId(signInResult.getData().userId());
            }
        }
        try {
            Doctor createdDoctor = doctorRepository.save(doctor);
            return doctorTransformer.convertModelToResponse(createdDoctor);
        } catch (DuplicateKeyException e) {
            log.error(String.format("Exception happened in creating user for %s", doctorRequest.getFirstName()), e
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
        Doctor updatedDoctor = doctorRepository.save(doctor);
        return doctorTransformer.convertModelToResponse(updatedDoctor);
    }

    @Override
    public void deleteDoctor(String id) {
        doctorRepository.deleteById(id);
    }
}
