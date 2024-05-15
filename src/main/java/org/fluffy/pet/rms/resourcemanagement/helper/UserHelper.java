package org.fluffy.pet.rms.resourcemanagement.helper;

import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignInEmailPassword;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignInMobilePassword;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignupInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.UpdatePasswordInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignInOutput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignupOutput;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.util.Result;

public interface UserHelper {
    Result<SignupOutput, ErrorCode> signup(SignupInput signupInput);

    Result<SignInOutput, ErrorCode> signIn(SignInEmailPassword signInEmailPassword);

    Result<SignInOutput, ErrorCode> signIn(SignInMobilePassword signInEmailPassword);

    Result<Void, ErrorCode> updatePassword(UpdatePasswordInput updatePasswordInput);
}
