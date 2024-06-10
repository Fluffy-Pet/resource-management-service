package org.fluffy.pet.rms.resourcemanagement.service;

import org.fluffy.pet.rms.resourcemanagement.dto.request.user.SignInViaEmailRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.SignInViaMobileRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.SignupViaEmailRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.SignupViaMobileRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.user.SignInResponse;

public interface UserService {
    SignInResponse signup(SignupViaMobileRequest signupViaMobileRequest);

    SignInResponse signup(SignupViaEmailRequest signupViaEmailRequest);

    SignInResponse signInViaMobile(SignInViaMobileRequest signInViaMobileRequest);

    SignInResponse signInViaMobile(SignInViaEmailRequest signInViaMobileRequest);
}
