package org.fluffy.pet.rms.resourcemanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import manager.file.FileManager;
import org.fluffy.pet.rms.resourcemanagement.configuration.contexts.UserContext;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.DoctorRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.DocumentResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.doctor.DoctorResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.helper.ClinicHelper;
import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;
import org.fluffy.pet.rms.resourcemanagement.model.common.AssociatedClinic;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Doctor;
import org.fluffy.pet.rms.resourcemanagement.repository.DoctorRepository;
import org.fluffy.pet.rms.resourcemanagement.service.DoctorService;
import org.fluffy.pet.rms.resourcemanagement.transformer.DoctorTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;


@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    private final DoctorTransformer doctorTransformer;

    private final ClinicHelper clinicHelper;

    private final UserContext userContext;

    private final FileManager fileManager;

    @Autowired
    public DoctorServiceImpl(
            DoctorRepository doctorRepository,
            DoctorTransformer doctorTransformer,
            ClinicHelper clinicHelper,
            FileManager fileManager,
            UserContext userContext
    ){
        this.doctorRepository = doctorRepository;
        this.doctorTransformer = doctorTransformer;
        this.clinicHelper=clinicHelper;
        this.userContext = userContext;
        this.fileManager = fileManager;
    }

    @Override
    public DoctorResponse getDoctor(String id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.DOCTOR_NOT_FOUND))
        );
        List<DocumentResponse> documentResponses = getDocumentResponses(doctor);
        return doctorTransformer.convertModelToResponse(doctor, getClinicsForDoctor(doctor), documentResponses);
    }

    @Override
    public DoctorResponse updateCurrentDoctor(DoctorRequest updateDoctorRequest) {
        Doctor doctor = doctorRepository.findById(userContext.getUserId()).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.DOCTOR_NOT_FOUND))
        );
        doctorTransformer.updateDoctor(doctor, updateDoctorRequest);
        Doctor updatedDoctor = doctorRepository.save(doctor);
        List<DocumentResponse> documentResponses = getDocumentResponses(updatedDoctor);
        return doctorTransformer.convertModelToResponse(updatedDoctor, getClinicsForDoctor(doctor), documentResponses);
    }

    @Override
    public void deleteDoctor(String id) {
        doctorRepository.deleteById(id);
    }

    private List<Clinic> getClinicsForDoctor(Doctor doctor){
        return clinicHelper.getClinics(StreamUtils.emptyIfNull(doctor.getAssociatedClinics()).map(AssociatedClinic::getClinicIds).toList());
    }

    private List<DocumentResponse> getDocumentResponses(Doctor doctor) {
        return StreamUtils.emptyIfNull(doctor.getDocuments()).map(document -> {
            URL url = fileManager.getFile(document.getDocumentFileName());
            return doctorTransformer.convertDocumentToResponse(document, url.toString());
        }).toList();
    }
}
