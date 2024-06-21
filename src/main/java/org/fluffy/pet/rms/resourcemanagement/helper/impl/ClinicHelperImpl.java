package org.fluffy.pet.rms.resourcemanagement.helper.impl;

import org.fluffy.pet.rms.resourcemanagement.annotations.Helper;
import org.fluffy.pet.rms.resourcemanagement.helper.ClinicHelper;
import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;
import org.fluffy.pet.rms.resourcemanagement.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Helper
public class ClinicHelperImpl implements ClinicHelper {

    private final ClinicRepository clinicRepository;

    @Autowired
    public ClinicHelperImpl(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Override
    public List<Clinic> getClinics(Set<String> clinicIds) {
        return clinicRepository.findAllByIds(clinicIds);
    }
}
