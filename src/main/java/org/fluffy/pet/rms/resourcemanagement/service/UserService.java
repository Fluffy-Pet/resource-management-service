package org.fluffy.pet.rms.resourcemanagement.service;

import org.fluffy.pet.rms.resourcemanagement.dto.request.user.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.user.SignInResponse;

public interface UserService {
    SignInResponse signup(SignupViaMobileRequest signupViaMobileRequest);

    SignInResponse signup(SignupViaEmailRequest signupViaEmailRequest);

    SignInResponse signIn(SignInViaMobileRequest signInViaMobileRequest);

    SignInResponse signIn(SignInViaEmailRequest signInViaMobileRequest);

    void updateCredentials(UpdatePasswordRequest updatePasswordRequest);

    void updateCredentials(UpdateEmailRequest updateEmailRequest);

    void updateCredentials(UpdateMobileRequest updateMobileRequest);
}