package org.fluffy.pet.rms.resourcemanagement.transformer;

import manager.authentication.models.JwtPayload;
import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.EmailInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.MobileInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignupInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignInOutput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignupOutput;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.UserEmailRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.UserMobileRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.SignupViaEmailRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.SignupViaMobileRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.user.SignInResponse;
import org.fluffy.pet.rms.resourcemanagement.model.User;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserEmail;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserMobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Transformer
public class UserTransformer {
    private final CommonTransformer commonTransformer;

    @Autowired
    public UserTransformer(CommonTransformer commonTransformer) {
        this.commonTransformer = commonTransformer;
    }

    public User convertSignupInputToModel(SignupInput signupInput, PasswordEncoder passwordEncoder) {
        return User
                .builder()
                .email(convertEmailInputToModel(signupInput.email()))
                .mobile(convertMobileInputToModel(signupInput.mobileInput()))
                .password(passwordEncoder.encode(signupInput.password()))
                .build();
    }

    public User convertSignupRequestViaMobileToModel(
            SignupViaMobileRequest signupViaMobileRequest
    ) {
        return User
                .builder()
                .mobile(convertMobileRequestToModel(signupViaMobileRequest.getUserMobileRequest()))
                .build();
    }

    public User convertSignupRequestViaEmailToModel(
            SignupViaEmailRequest signupViaEmailRequest
    ) {
        return User
                .builder()
                .email(convertEmailRequestToModel(signupViaEmailRequest.getUserEmailRequest()))
                .build();
    }

    public UserEmail convertEmailInputToModel(EmailInput emailInput) {
        return commonTransformer.convertEmailInputToModel(emailInput);
    }

    public UserMobile convertMobileInputToModel(MobileInput mobileInput) {
        return commonTransformer.convertMobileInputToModel(mobileInput);
    }

    public UserEmail convertEmailRequestToModel(UserEmailRequest userEmailRequest) {
        return commonTransformer.convertEmailRequestToModel(userEmailRequest);
    }

    public UserMobile convertMobileRequestToModel(UserMobileRequest userMobileRequest) {
        return commonTransformer.convertMobileRequestToModel(userMobileRequest);
    }

    public JwtPayload convertUserToJwtPayload(User user) {
        return JwtPayload.builder().sub(user.getId()).build();
    }

    public SignupOutput convertUserToSignupOutput(String id,String token) {
        return new SignupOutput(id, token);
    }

    public SignInOutput convertUserToSigninOutput(String id,String token) {
        return new SignInOutput(id,token);
    }

    public SignInResponse convertUserToSignInResponse(String jwtToken) {
        return SignInResponse.builder().token(jwtToken).build();
    }
}
