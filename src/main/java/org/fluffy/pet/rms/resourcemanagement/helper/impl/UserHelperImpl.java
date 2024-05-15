package org.fluffy.pet.rms.resourcemanagement.helper.impl;

import manager.authentication.JwtAuthenticationManager;
import manager.authentication.models.JwtPayload;
import org.fluffy.pet.rms.resourcemanagement.annotations.Helper;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignInEmailPassword;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignInMobilePassword;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignupInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.UpdatePasswordInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignInOutput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignupOutput;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.helper.UserHelper;
import org.fluffy.pet.rms.resourcemanagement.model.User;
import org.fluffy.pet.rms.resourcemanagement.model.common.Email;
import org.fluffy.pet.rms.resourcemanagement.model.common.Mobile;
import org.fluffy.pet.rms.resourcemanagement.repository.UserRepository;
import org.fluffy.pet.rms.resourcemanagement.transformer.UserTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.util.Optional;

@Helper
public class UserHelperImpl implements UserHelper {
    private final UserRepository userRepository;

    private final UserTransformer userTransformer;

    private final JwtAuthenticationManager jwtAuthenticationManager;

    @Autowired
    public UserHelperImpl(UserRepository userRepository, UserTransformer userTransformer, JwtAuthenticationManager jwtAuthenticationManager) {
        this.userRepository = userRepository;
        this.userTransformer = userTransformer;
        this.jwtAuthenticationManager = jwtAuthenticationManager;
    }

    @Override
    public Result<SignupOutput, ErrorCode> signup(SignupInput signupInput) {
        User user = userTransformer.convertSignupInputToModel(signupInput);
        user.setStatus(Status.ACTIVE);
        try {
            User createdUser = userRepository.save(user);
            JwtPayload jwtPayload = userTransformer.convertUserToJwtPayload(createdUser);
            String jwtToken = jwtAuthenticationManager.createJwtToken(jwtPayload);
            SignupOutput signupOutput = userTransformer.convertUserToSignupOutput(jwtToken);
            return Result.success(signupOutput);
        } catch (DuplicateKeyException e) {
            return Result.error(ErrorCode.DUPLICATE_USER);
        }
    }

    @Override
    public Result<SignInOutput, ErrorCode> signIn(SignInEmailPassword signInEmailPassword) {
        Email email = userTransformer.convertInputToEmail(signInEmailPassword.email());
        Optional<User> userByEmail = userRepository.findUserByEmail(email);
        if (userByEmail.isEmpty()) {
            return Result.error(ErrorCode.USER_NOT_FOUND);
        }
        User user = userByEmail.get();
        return verifyPasswordAndSignInUser(user, signInEmailPassword.password());
    }

    @Override
    public Result<SignInOutput, ErrorCode> signIn(SignInMobilePassword signInMobilePassword) {
        Mobile mobile = userTransformer.convertInputToMobile(signInMobilePassword.mobile());
        Optional<User> userByMobile = userRepository.findUserByMobile(mobile);
        if (userByMobile.isEmpty()) {
            return Result.error(ErrorCode.USER_NOT_FOUND);
        }
        User user = userByMobile.get();
        return verifyPasswordAndSignInUser(user, signInMobilePassword.password());
    }

    @Override
    public Result<Void, ErrorCode> updatePassword(UpdatePasswordInput updatePasswordInput) {
        Optional<User> optionalUser = userRepository.findById(updatePasswordInput.userId());
        if (optionalUser.isEmpty()) {
            return Result.error(ErrorCode.USER_NOT_FOUND);
        }
        User user = optionalUser.get();
        user.setPassword(updatePasswordInput.newPassword());
        userRepository.save(user);
        return Result.success(null);
    }

    private Result<SignInOutput, ErrorCode> verifyPasswordAndSignInUser(User user, String password) {
        if (user.getPassword().equals(password)) {
            JwtPayload jwtPayload = userTransformer.convertUserToJwtPayload(user);
            String jwtToken = jwtAuthenticationManager.createJwtToken(jwtPayload);
            SignInOutput signInOutput = userTransformer.convertUserToSigninOutput(jwtToken);
            return Result.success(signInOutput);
        }
        return Result.error(ErrorCode.UN_AUTHORISED);
    }
}
