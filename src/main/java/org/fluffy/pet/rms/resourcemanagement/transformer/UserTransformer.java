package org.fluffy.pet.rms.resourcemanagement.transformer;

import manager.authentication.models.JwtPayload;
import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.EmailRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.MobileRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.SignupViaEmailRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.SignupViaMobileRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.user.SignInResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;
import org.fluffy.pet.rms.resourcemanagement.model.User;
import org.fluffy.pet.rms.resourcemanagement.model.common.Email;
import org.fluffy.pet.rms.resourcemanagement.model.common.Mobile;
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
                .mobile(convertMobileRequestToModel(signupViaMobileRequest.getMobile()))
                .mobileValid(false)
                .emailValid(false)
                .build();
    }

    public User convertSignupRequestViaEmailToModel(
            SignupViaEmailRequest signupViaEmailRequest
    ) {
        return User
                .builder()
                .email(convertEmailRequestToModel(signupViaEmailRequest.getEmail()))
                .mobileValid(false)
                .emailValid(false)
                .build();
    }

    public Email convertEmailRequestToModel(EmailRequest emailRequest) {
        return commonTransformer.convertEmailRequestToModel(emailRequest);
    }

    public Mobile convertMobileRequestToModel(MobileRequest mobileRequest) {
        return commonTransformer.convertMobileRequestToModel(mobileRequest);
    }

    public JwtPayload convertUserToJwtPayload(User user, UserType userType) {
        return JwtPayload.builder().sub(user.getId()).userType(userType).build();
    }

    public SignInResponse convertUserToSignInResponse(String jwtToken) {
        return SignInResponse.builder().token(jwtToken).build();
    }
}
