package org.fluffy.pet.rms.resourcemanagement.transformer;

import manager.authentication.models.JwtPayload;
import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.UserEmailRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.UserMobileRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.SignupViaEmailRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.SignupViaMobileRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.user.SignInResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;
import org.fluffy.pet.rms.resourcemanagement.model.User;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserEmail;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserMobile;
import org.springframework.beans.factory.annotation.Autowired;

@Transformer
public class UserTransformer {
    private final CommonTransformer commonTransformer;

    @Autowired
    public UserTransformer(CommonTransformer commonTransformer) {
        this.commonTransformer = commonTransformer;
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

    public UserEmail convertEmailRequestToModel(UserEmailRequest userEmailRequest) {
        return commonTransformer.convertEmailRequestToModel(userEmailRequest);
    }

    public UserMobile convertMobileRequestToModel(UserMobileRequest userMobileRequest) {
        return commonTransformer.convertMobileRequestToModel(userMobileRequest);
    }

    public JwtPayload convertUserToJwtPayload(User user, UserType userType) {
        return JwtPayload.builder().sub(user.getId()).userType(userType).build();
    }

    public SignInResponse convertUserToSignInResponse(String jwtToken) {
        return SignInResponse.builder().token(jwtToken).build();
    }
}
