package org.fluffy.pet.rms.resourcemanagement.helper;

import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;

import java.util.List;
import java.util.Set;

public interface ClinicHelper {
    List<Clinic> getClinics(Set<String> clinicIds);
}
