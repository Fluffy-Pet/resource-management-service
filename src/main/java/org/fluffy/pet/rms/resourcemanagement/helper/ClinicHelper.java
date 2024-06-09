package org.fluffy.pet.rms.resourcemanagement.helper;

import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;

import java.util.List;

public interface ClinicHelper {
    List<Clinic> getClinics(List<String> clinicIds);
}
