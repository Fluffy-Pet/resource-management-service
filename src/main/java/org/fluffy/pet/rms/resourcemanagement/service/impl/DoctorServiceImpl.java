package org.fluffy.pet.rms.resourcemanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.UserEmailRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.UserMobileRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.DoctorRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.doctor.DoctorResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.helper.ClinicHelper;
import org.fluffy.pet.rms.resourcemanagement.helper.UserHelper;
import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Doctor;
import org.fluffy.pet.rms.resourcemanagement.repository.DoctorRepository;
import org.fluffy.pet.rms.resourcemanagement.service.DoctorService;
import org.fluffy.pet.rms.resourcemanagement.transformer.CommonTransformer;
import org.fluffy.pet.rms.resourcemanagement.transformer.DoctorTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    private final DoctorTransformer doctorTransformer;

    private final CommonTransformer commonTransformer;

    private final UserHelper userHelper;

    private final ClinicHelper clinicHelper;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorTransformer doctorTransformer,UserHelper userHelper, CommonTransformer commonTransformer, ClinicHelper clinicHelper){
        this.doctorRepository = doctorRepository;
        this.doctorTransformer = doctorTransformer;
        this.userHelper= userHelper;
        this.commonTransformer=commonTransformer;
        this.clinicHelper=clinicHelper;
    }

    @Override
    public <Q> DoctorResponse createDoctor(DoctorRequest<Q> doctorRequest) {
        Doctor doctor = doctorTransformer.convertRequestToModel(doctorRequest);
        String password = doctorRequest.getPassword();
        Optional<String> userId = switch (doctorRequest.getSignupUserInfo()) {
            case UserEmailRequest userEmailRequest -> UserUtils.fetchUserIdForSignup(
                    doctorRequest,
                    commonTransformer.convertRequestToSignInEmailPassword(userEmailRequest, password),
                    doctorTransformer::convertRequestToSignupInput,
                    userHelper::signup,
                    userHelper::signIn
            );
            case UserMobileRequest userMobileRequest -> UserUtils.fetchUserIdForSignup(
                    doctorRequest,
                    commonTransformer.convertRequestToSignInMobilePassword(userMobileRequest, password),
                    doctorTransformer::convertRequestToSignupInput,
                    userHelper::signup,
                    userHelper::signIn
            );
            default -> throw new RestException(HttpStatus.BAD_REQUEST, ErrorResponse.from(ErrorCode.INPUT_VALIDATION_ERROR));
        };
        if (userId.isEmpty()) {
            throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorResponse.from(ErrorCode.INTERNAL_SERVER_ERROR));
        }
        doctor.setId(userId.get());
        doctor.setStatus(Status.ACTIVE);
        try {
            Doctor createdDoctor = doctorRepository.save(doctor);
            return doctorTransformer.convertModelToResponse(createdDoctor,getClinicsForDoctor(createdDoctor));
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
        return doctorTransformer.convertModelToResponse(doctor,getClinicsForDoctor(doctor));
    }

    @Override
    public <T> DoctorResponse updateDoctor(DoctorRequest<T> updateDoctorRequest, String id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.DOCTOR_NOT_FOUND))
        );
        doctorTransformer.updateDoctor(doctor, updateDoctorRequest);
        Doctor updatedDoctor = doctorRepository.save(doctor);
        return doctorTransformer.convertModelToResponse(updatedDoctor,getClinicsForDoctor(doctor));
    }

    @Override
    public void deleteDoctor(String id) {
        doctorRepository.deleteById(id);
    }

    private List<Clinic> getClinicsForDoctor(Doctor doctor){
        return clinicHelper.getClinics(doctor.getAssociatedClinics().getClinicIds());
    }
}
