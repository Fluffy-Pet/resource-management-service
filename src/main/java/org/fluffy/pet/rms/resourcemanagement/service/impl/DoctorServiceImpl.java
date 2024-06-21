package org.fluffy.pet.rms.resourcemanagement.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import manager.file.FileManager;
import org.fluffy.pet.rms.resourcemanagement.configuration.contexts.UserContext;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.DoctorRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.DocumentResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.doctor.DoctorResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.exception.AppException;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.helper.ClinicHelper;
import org.fluffy.pet.rms.resourcemanagement.helper.FilterHelper;
import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;
import org.fluffy.pet.rms.resourcemanagement.model.common.AssociatedClinic;
import org.fluffy.pet.rms.resourcemanagement.model.staff.Doctor;
import org.fluffy.pet.rms.resourcemanagement.repository.DoctorRepository;
import org.fluffy.pet.rms.resourcemanagement.service.DoctorService;
import org.fluffy.pet.rms.resourcemanagement.transformer.DoctorTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.PaginationWrapper;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    private final DoctorTransformer doctorTransformer;

    private final ClinicHelper clinicHelper;

    private final FilterHelper<DoctorResponse> filterHelper;

    private final UserContext userContext;

    private final FileManager fileManager;

    @Autowired
    public DoctorServiceImpl(
            DoctorRepository doctorRepository,
            DoctorTransformer doctorTransformer,
            ClinicHelper clinicHelper,
            FilterHelper<DoctorResponse> filterHelper,
            FileManager fileManager,
            UserContext userContext
    ){
        this.doctorRepository = doctorRepository;
        this.doctorTransformer = doctorTransformer;
        this.clinicHelper=clinicHelper;
        this.filterHelper = filterHelper;
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

    @Override
    public PaginationWrapper<List<JsonNode>> filterDoctors(FilterRequest filterRequest) {
        try {
            return filterHelper.filterEntities(
                    filterRequest,
                    this::convertModelToResponseFromFilterRequest
            );
        } catch (AppException appException) {
            ErrorResponse errorResponse = ErrorResponse.from(ErrorCode.INVALID_FILTER_REQUEST);
            errorResponse.setDetail(appException.getMessage());
            throw new RestException(HttpStatus.BAD_REQUEST, errorResponse);
        }
    }

    private Page<DoctorResponse> convertModelToResponseFromFilterRequest(FilterRequest filterRequest) {
        Page<Doctor> doctors = doctorRepository.filterDocuments(
                filterRequest.getFilters(),
                filterRequest.getSort(),
                filterRequest.getPage(),
                filterRequest.getPageSize()
        );
        List<Clinic> clinics = clinicHelper.getClinics(
                doctors.stream().flatMap(doctor -> doctor.getAssociatedClinics().stream().map(AssociatedClinic::getClinicIds)).collect(Collectors.toSet())
        );
        return doctors.map(doctor -> {
            List<DocumentResponse> documentResponses = getDocumentResponses(doctor);
            return doctorTransformer.convertModelToResponse(doctor, clinics, documentResponses);
        });
    }

    private List<Clinic> getClinicsForDoctor(Doctor doctor){
        return clinicHelper.getClinics(StreamUtils.emptyIfNull(doctor.getAssociatedClinics()).map(AssociatedClinic::getClinicIds).collect(Collectors.toSet()));
    }

    private List<DocumentResponse> getDocumentResponses(Doctor doctor) {
        return StreamUtils.emptyIfNull(doctor.getDocuments()).map(document -> {
            URL url = fileManager.getFile(document.getDocumentFileName());
            return doctorTransformer.convertDocumentToResponse(document, url.toString());
        }).toList();
    }
}
