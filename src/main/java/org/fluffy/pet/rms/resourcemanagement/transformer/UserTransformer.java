package org.fluffy.pet.rms.resourcemanagement.transformer;

import manager.authentication.models.JwtPayload;
import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.EmailInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.MobileInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignupInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignInOutput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignupOutput;
import org.fluffy.pet.rms.resourcemanagement.model.User;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserEmail;
import org.fluffy.pet.rms.resourcemanagement.model.common.UserMobile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Transformer
public class UserTransformer {
    public User convertSignupInputToModel(SignupInput signupInput, PasswordEncoder passwordEncoder) {
        return User
                .builder()
                .emailId(convertEmailInputToModel(signupInput.email()))
                .mobile(convertMobileInputToModel(signupInput.mobileInput()))
                .password(passwordEncoder.encode(signupInput.password()))
                .build();
    }
    public UserEmail convertEmailInputToModel(EmailInput emailInput) {
        return UserEmail.builder().email(emailInput.emailId()).build();
    }

    public UserMobile convertMobileInputToModel(MobileInput mobileInput) {
        return UserMobile.builder().mobileNumber(mobileInput.mobileNumber())
                .countryCode(mobileInput.countryCode()).build();
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
}
