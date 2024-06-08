package org.fluffy.pet.rms.resourcemanagement.helper.impl;

import org.fluffy.pet.rms.resourcemanagement.annotations.Helper;
import org.fluffy.pet.rms.resourcemanagement.helper.ClinicHelper;
import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;
import org.fluffy.pet.rms.resourcemanagement.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Helper
public class ClinicHelperImpl implements ClinicHelper {

    private final ClinicRepository clinicRepository;

    @Autowired
    public ClinicHelperImpl(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Override
    public List<Clinic> getClinics(List<String> clinicIds) {
        return clinicRepository.findAllByIds(clinicIds);
    }
}
