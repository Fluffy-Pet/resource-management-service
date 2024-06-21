package org.fluffy.pet.rms.resourcemanagement.helper.impl;

import lombok.extern.slf4j.Slf4j;
import manager.file.FileManager;
import org.fluffy.pet.rms.resourcemanagement.annotations.Helper;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.DocumentResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.doctor.DoctorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.helper.ClinicHelper;
import org.fluffy.pet.rms.resourcemanagement.helper.DoctorHelper;
import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;
import org.fluffy.pet.rms.resourcemanagement.model.common.AssociatedClinic;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Doctor;
import org.fluffy.pet.rms.resourcemanagement.repository.DoctorRepository;
import org.fluffy.pet.rms.resourcemanagement.transformer.DoctorTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.Result;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Helper
@Slf4j
public class DoctorHelperImpl implements DoctorHelper {
    private final DoctorRepository doctorRepository;

    private final DoctorTransformer doctorTransformer;

    private final ClinicHelper clinicHelper;

    private final FileManager fileManager;

    @Autowired
    public DoctorHelperImpl(DoctorRepository doctorRepository, DoctorTransformer doctorTransformer, ClinicHelper clinicHelper, FileManager fileManager) {
        this.doctorRepository = doctorRepository;
        this.doctorTransformer = doctorTransformer;
        this.clinicHelper = clinicHelper;
        this.fileManager = fileManager;
    }

    @Override
    public Result<Void, ErrorCode> createUserEntityForIdOnly(String userId) {
        Doctor doctor = Doctor.builder().id(userId).status(Status.ACTIVE).build();
        try {
            Doctor createdDoctor = doctorRepository.save(doctor);
            log.info("Successfully created empty doctor with id {}", createdDoctor.getId());
            return Result.success(null);
        } catch (DuplicateKeyException duplicateKeyException) {
            return Result.error(ErrorCode.DUPLICATE_USER);
        }
    }

    @Override
    public boolean checkUserEntityExists(String userEntityId) {
        return doctorRepository.exists(userEntityId);
    }

    @Override
    public Result<DoctorResponse, ErrorCode> getUserEntityById(String userId) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(userId);
        if (optionalDoctor.isEmpty()) {
            return Result.error(ErrorCode.USER_NOT_FOUND);
        }
        Doctor doctor = optionalDoctor.get();
        List<Clinic> clinics = clinicHelper.getClinics(StreamUtils.emptyIfNull(doctor.getAssociatedClinics()).map(AssociatedClinic::getClinicIds).collect(Collectors.toSet()));
        return Result.success(doctorTransformer.convertModelToResponse(doctor, clinics, getDocumentResponses(doctor)));
    }

    private List<DocumentResponse> getDocumentResponses(Doctor doctor) {
        return StreamUtils.emptyIfNull(doctor.getDocuments()).map(document -> {
            URL url = fileManager.getFile(document.getDocumentFileName());
            return doctorTransformer.convertDocumentToResponse(document, url.toString());
        }).toList();
    }
}
