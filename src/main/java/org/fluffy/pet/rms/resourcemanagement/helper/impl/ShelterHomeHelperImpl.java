package org.fluffy.pet.rms.resourcemanagement.helper.impl;

import org.fluffy.pet.rms.resourcemanagement.annotations.Helper;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.helper.ShelterHomeHelper;
import org.fluffy.pet.rms.resourcemanagement.model.common.ProviderIdentity;
import org.fluffy.pet.rms.resourcemanagement.model.shelter.ShelterHome;
import org.fluffy.pet.rms.resourcemanagement.repository.ShelterHomeRepository;
import org.fluffy.pet.rms.resourcemanagement.transformer.ShelterHomeTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Helper
public class ShelterHomeHelperImpl implements ShelterHomeHelper {
    private final ShelterHomeRepository shelterHomeRepository;

    private final ShelterHomeTransformer shelterHomeTransformer;

    @Autowired
    public ShelterHomeHelperImpl(ShelterHomeRepository shelterHomeRepository, ShelterHomeTransformer shelterHomeTransformer) {
        this.shelterHomeRepository = shelterHomeRepository;
        this.shelterHomeTransformer = shelterHomeTransformer;
    }

    @Override
    public Result<ProviderIdentity, ErrorCode> getProviderIdentityById(String shelterHomeId) {
        Optional<ShelterHome> optionalShelterHome = shelterHomeRepository.findById(shelterHomeId);
        if (optionalShelterHome.isEmpty()) {
            return Result.error(ErrorCode.SHELTER_HOME_NOT_FOUND);
        }
        return Result.success(shelterHomeTransformer.convertModelToIdentity(optionalShelterHome.get()));
    }
}
