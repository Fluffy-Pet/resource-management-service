package org.fluffy.pet.rms.resourcemanagement.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.fluffy.pet.rms.resourcemanagement.configuration.contexts.UserContext;
import org.fluffy.pet.rms.resourcemanagement.dto.request.clinic.ClinicRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.clinic.ClinicResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;
import org.fluffy.pet.rms.resourcemanagement.exception.AppException;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.helper.FilterHelper;
import org.fluffy.pet.rms.resourcemanagement.helper.UserHelper;
import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserIdentity;
import org.fluffy.pet.rms.resourcemanagement.repository.ClinicRepository;
import org.fluffy.pet.rms.resourcemanagement.service.ClinicService;
import org.fluffy.pet.rms.resourcemanagement.transformer.ClinicTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.PaginationWrapper;
import org.fluffy.pet.rms.resourcemanagement.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClinicServiceImpl implements ClinicService {

    private final ClinicRepository clinicRepository;

    private final ClinicTransformer clinicTransformer;

    private final FilterHelper<ClinicResponse> filterHelper;

    private final UserHelper userHelper;

    private final UserContext userContext;

    @Autowired
    public ClinicServiceImpl(ClinicRepository clinicRepository, ClinicTransformer clinicTransformer, FilterHelper<ClinicResponse> filterHelper, UserHelper userHelper, UserContext userContext){
        this.clinicRepository = clinicRepository;
        this.clinicTransformer = clinicTransformer;
        this.filterHelper = filterHelper;
        this.userHelper = userHelper;
        this.userContext = userContext;
    }

    @Override
    public ClinicResponse createClinic(ClinicRequest clinicRequest) {
        Clinic clinic = clinicTransformer.convertRequestToModel(clinicRequest, userContext.getUserId());
        try {
            Clinic createdClinic = clinicRepository.save(clinic);
            return getClinicResponse(createdClinic);
        } catch (DuplicateKeyException e) {
            log.error(String.format("Exception happened in creating user for %s", clinicRequest.getClinicName()), e
            );
            throw new RestException(HttpStatus.BAD_REQUEST, ErrorResponse.from(ErrorCode.CLINIC_ALREADY_EXISTS));
        }
    }

    @Override
    public ClinicResponse getClinic(String id) {
        Clinic clinic = clinicRepository.findById(id).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.CLINIC_NOT_FOUND))
        );
        return getClinicResponse(clinic);
    }

    @Override
    public ClinicResponse updateClinic(ClinicRequest updateClinicRequest, String id) {
        Clinic clinic = clinicRepository.findById(id).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.CLINIC_NOT_FOUND))
        );
        clinicTransformer.updateClinic(clinic, updateClinicRequest);
        Clinic updatedClinic = clinicRepository.save(clinic);
        return getClinicResponse(updatedClinic);
    }

    @Override
    public void deleteClinic(String id) {
        clinicRepository.deleteById(id);
    }

    @Override
    public PaginationWrapper<List<JsonNode>> filterClinics(FilterRequest filterRequest) {
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

    private Page<ClinicResponse> convertModelToResponseFromFilterRequest(FilterRequest filterRequest) {
        Page<Clinic> clinics = clinicRepository.filterDocuments(
                filterRequest.getFilters(),
                filterRequest.getSort(),
                filterRequest.getPage(),
                filterRequest.getPageSize()
        );
        return clinics.map(this::getClinicResponse);
    }

    private ClinicResponse getClinicResponse(Clinic createdClinic) {
        Result<UserIdentity, ErrorCode> userIdentityResult = userHelper.getUserIdentity(userContext.getUserId(), UserType.ADMIN);
        if (userIdentityResult.isFailure()) {
            throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorResponse.from(userIdentityResult.getError()));
        }
        return clinicTransformer.convertModelToResponse(createdClinic, userIdentityResult.getData());
    }
}
