package org.fluffy.pet.rms.resourcemanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.fluffy.pet.rms.resourcemanagement.dto.request.shelter.ShelterHomeRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.shelter.ShelterHomeResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.model.shelter.ShelterHome;
import org.fluffy.pet.rms.resourcemanagement.repository.ShelterHomeRepository;
import org.fluffy.pet.rms.resourcemanagement.service.ShelterHomeService;
import org.fluffy.pet.rms.resourcemanagement.transformer.ShelterHomeTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShelterHomeServiceImpl implements ShelterHomeService {
    private final ShelterHomeRepository shelterHomeRepository;

    private final ShelterHomeTransformer shelterHomeTransformer;

    @Autowired
    public ShelterHomeServiceImpl(ShelterHomeRepository shelterHomeRepository, ShelterHomeTransformer shelterHomeTransformer){
        this.shelterHomeRepository = shelterHomeRepository;
        this.shelterHomeTransformer = shelterHomeTransformer;
    }

    @Override
    public ShelterHomeResponse createShelterHome(ShelterHomeRequest shelterHomeRequest) {
        ShelterHome shelterHome = shelterHomeTransformer.convertRequestToModel(shelterHomeRequest);
        try {
            ShelterHome createdShelterHome = shelterHomeRepository.save(shelterHome);
            return shelterHomeTransformer.convertModelToResponse(createdShelterHome);
        } catch (DuplicateKeyException e) {
            log.error(String.format("Exception happened in creating user for %s", shelterHomeRequest.getName()), e
            );
            throw new RestException(HttpStatus.BAD_REQUEST, ErrorResponse.from(ErrorCode.SHELTER_ALREADY_EXISTS));
        }
    }

    @Override
    public ShelterHomeResponse getShelterHome(String id) {
        ShelterHome shelterHome = shelterHomeRepository.findById(id).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.SHELTER_HOME_NOT_FOUND))
        );
        return shelterHomeTransformer.convertModelToResponse(shelterHome);
    }

    @Override
    public ShelterHomeResponse updateShelterHome(ShelterHomeRequest shelterHomeRequest,String id) {
        ShelterHome shelterHome = shelterHomeRepository.findById(id).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.SHELTER_HOME_NOT_FOUND))
        );
        shelterHomeTransformer.updateShelterHome(shelterHome, shelterHomeRequest);
        ShelterHome updatedShelterHome = shelterHomeRepository.save(shelterHome);
        return shelterHomeTransformer.convertModelToResponse(updatedShelterHome);
    }

    @Override
    public void deleteShelterHome(String id) {
        shelterHomeRepository.deleteById(id);
    }
}
