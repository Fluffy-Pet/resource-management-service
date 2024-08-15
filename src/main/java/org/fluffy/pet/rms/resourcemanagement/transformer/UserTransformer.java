package org.fluffy.pet.rms.resourcemanagement.transformer;

import manager.authentication.models.JwtPayload;
import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.EmailRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.MobileRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.SignupViaEmailRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.SignupViaMobileRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.user.SignInResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.user.UserResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;
import org.fluffy.pet.rms.resourcemanagement.model.User;
import org.fluffy.pet.rms.resourcemanagement.model.common.Email;
import org.fluffy.pet.rms.resourcemanagement.model.common.Mobile;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

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
                .id(UUID.randomUUID().toString())
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
                .id(UUID.randomUUID().toString())
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

    public <T> UserResponse<T> convertModelToResponse(User user, T data) {
        UserResponse<T> userResponse = new UserResponse<>();
        userResponse.setUserId(user.getId());
        userResponse.setUserData(data);
        userResponse.setEmail(ObjectUtils.transformIfNotNull(user.getEmail(), commonTransformer::convertModelToResponse));
        userResponse.setMobile(ObjectUtils.transformIfNotNull(user.getMobile(), commonTransformer::convertModelToResponse));
        userResponse.setEmailValid(user.getEmailValid());
        userResponse.setMobileValid(user.getMobileValid());
        return userResponse;
    }
}
