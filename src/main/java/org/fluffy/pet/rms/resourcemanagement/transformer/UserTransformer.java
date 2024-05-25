package org.fluffy.pet.rms.resourcemanagement.transformer;

import manager.authentication.models.JwtPayload;
import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignupInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignInOutput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignupOutput;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.UserRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.user.UserResponse;
import org.fluffy.pet.rms.resourcemanagement.model.User;

@Transformer
public class UserTransformer {
    public User convertSignupInputToModel(SignupInput signupInput) {
        return User
                .builder()
                .emailId(signupInput.email().emailId())
                .mobile(signupInput.mobileInput().toString())
                .password(signupInput.password())
                .build();
    }

    public UserResponse convertModelToResponse(User user){
        return UserResponse
                .builder()
                .mobile(user.getMobile())
                .emailId(user.getEmailId())
                .build();
    }

    public void updateUser(User user, UserRequest userRequest){
        user.setEmailId(userRequest.getEmailId());
        user.setMobile(userRequest.getMobile());
    }

    public JwtPayload convertUserToJwtPayload(User user) {
        return JwtPayload.builder().sub(user.getId()).build();
    }

    public SignupOutput convertUserToSignupOutput(String token) {
        return new SignupOutput(token);
    }

    public SignInOutput convertUserToSigninOutput(String token) {
        return new SignInOutput(token);
    }
}
