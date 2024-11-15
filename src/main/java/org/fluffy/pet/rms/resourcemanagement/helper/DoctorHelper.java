package org.fluffy.pet.rms.resourcemanagement.helper;

import org.fluffy.pet.rms.resourcemanagement.dto.response.doctor.DoctorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.model.common.ProviderIdentity;
import org.fluffy.pet.rms.resourcemanagement.util.Result;

public interface DoctorHelper extends BaseUserEntityHelper<DoctorResponse> {
    Result<ProviderIdentity, ErrorCode> getProviderIdentityById(String doctorId);
}
