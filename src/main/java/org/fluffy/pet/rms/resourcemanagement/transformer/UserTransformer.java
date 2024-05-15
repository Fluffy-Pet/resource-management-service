package org.fluffy.pet.rms.resourcemanagement.transformer;

import manager.authentication.models.JwtPayload;
import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.EmailInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.MobileInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignupInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignInOutput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignupOutput;
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

    public Mobile convertInputToMobile(MobileInput mobileInput) {
        return commonTransformer.convertInputToMobile(mobileInput);
    }

    public Email convertInputToEmail(EmailInput emailInput) {
        return commonTransformer.convertInputToEmail(emailInput);
    }

    public User convertSignupInputToModel(SignupInput signupInput) {
        return User
                .builder()
                .email(commonTransformer.convertInputToEmail(signupInput.email()))
                .mobile(commonTransformer.convertInputToMobile(signupInput.mobileInput()))
                .password(signupInput.password())
                .build();
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
