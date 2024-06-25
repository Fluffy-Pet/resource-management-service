package org.fluffy.pet.rms.resourcemanagement.helper;

import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.model.clinic.Clinic;
import org.fluffy.pet.rms.resourcemanagement.model.common.ProviderIdentity;
import org.fluffy.pet.rms.resourcemanagement.util.Result;

import java.util.List;
import java.util.Set;

public interface ClinicHelper {
    Result<ProviderIdentity, ErrorCode> getProviderIdentityById(String clinicId);

    List<Clinic> getClinics(Set<String> clinicIds);
}
