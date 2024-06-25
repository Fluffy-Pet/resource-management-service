package org.fluffy.pet.rms.resourcemanagement.helper.impl;

import org.fluffy.pet.rms.resourcemanagement.annotations.Helper;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.helper.ClinicHelper;
import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;
import org.fluffy.pet.rms.resourcemanagement.model.common.ProviderIdentity;
import org.fluffy.pet.rms.resourcemanagement.repository.ClinicRepository;
import org.fluffy.pet.rms.resourcemanagement.transformer.ClinicTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Helper
public class ClinicHelperImpl implements ClinicHelper {

    private final ClinicRepository clinicRepository;

    private final ClinicTransformer clinicTransformer;

    @Autowired
    public ClinicHelperImpl(ClinicRepository clinicRepository, ClinicTransformer clinicTransformer) {
        this.clinicRepository = clinicRepository;
        this.clinicTransformer = clinicTransformer;
    }

    @Override
    public Result<ProviderIdentity, ErrorCode> getProviderIdentityById(String clinicId) {
        Optional<Clinic> optionalClinic = clinicRepository.findById(clinicId);
        if (optionalClinic.isEmpty()) {
            return Result.error(ErrorCode.CLIENT_NOT_FOUND);
        }
        return Result.success(clinicTransformer.convertModelToIdentity(optionalClinic.get()));
    }

    @Override
    public List<Clinic> getClinics(Set<String> clinicIds) {
        return clinicRepository.findAllByIds(clinicIds);
    }
}
