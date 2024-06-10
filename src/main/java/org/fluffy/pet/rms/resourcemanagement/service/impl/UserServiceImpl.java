package org.fluffy.pet.rms.resourcemanagement.service.impl;

import manager.authentication.JwtAuthenticationManager;
import manager.authentication.models.JwtPayload;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.SignInViaEmailRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.SignInViaMobileRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.SignupViaEmailRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.SignupViaMobileRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.user.SignInResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.model.User;
import org.fluffy.pet.rms.resourcemanagement.repository.UserRepository;
import org.fluffy.pet.rms.resourcemanagement.service.UserService;
import org.fluffy.pet.rms.resourcemanagement.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserTransformer userTransformer;

    private final PasswordEncoder passwordEncoder;

    private final JwtAuthenticationManager jwtAuthenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserTransformer userTransformer, PasswordEncoder passwordEncoder, JwtAuthenticationManager jwtAuthenticationManager) {
        this.userRepository = userRepository;
        this.userTransformer = userTransformer;
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthenticationManager = jwtAuthenticationManager;
    }

    @Override
    public SignInResponse signup(SignupViaMobileRequest signupViaMobileRequest) {
        return signupUserOrThrowException(
                signupViaMobileRequest,
                signupViaMobileRequest.getPassword(),
                signupViaMobileRequest.getUserType(),
                userTransformer::convertSignupRequestViaMobileToModel
        );
    }

    @Override
    public SignInResponse signup(SignupViaEmailRequest signupViaEmailRequest) {
        return signupUserOrThrowException(
                signupViaEmailRequest,
                signupViaEmailRequest.getPassword(),
                signupViaEmailRequest.getUserType(),
                userTransformer::convertSignupRequestViaEmailToModel
        );
    }

    @Override
    public SignInResponse signInViaMobile(SignInViaMobileRequest signInViaMobileRequest) {
        return signInUserOrThrowException(
                signInViaMobileRequest.getUserMobileRequest(),
                signInViaMobileRequest.getPassword(),
                userTransformer::convertMobileRequestToModel,
                userRepository::findUserByMobile
        );
    }

    @Override
    public SignInResponse signInViaMobile(SignInViaEmailRequest signInViaMobileRequest) {
        return signInUserOrThrowException(
                signInViaMobileRequest.getUserEmailRequest(),
                signInViaMobileRequest.getPassword(),
                userTransformer::convertEmailRequestToModel,
                userRepository::findUserByEmail
        );
    }

    private <T> SignInResponse signupUserOrThrowException(
            T signupRequest,
            String password,
            UserType userType,
            Function<T, User> convertRequestToUser
    ) {
        User user = convertRequestToUser.apply(signupRequest);
        user.setPassword(passwordEncoder.encode(password));
        user.setStatus(Status.ACTIVE);
        try {
            User createdUser = userRepository.save(user);
            // Create Entity or if exists then get entity
            JwtPayload jwtPayload = userTransformer.convertUserToJwtPayload(createdUser);
            String jwtToken = jwtAuthenticationManager.createJwtToken(jwtPayload);
            return userTransformer.convertUserToSignInResponse(jwtToken);
        } catch (DuplicateKeyException e) {
            throw new RestException(HttpStatus.BAD_REQUEST, ErrorResponse.from(ErrorCode.DUPLICATE_USER));
        }
    }

    private <T, R> SignInResponse signInUserOrThrowException(
            T signInRequest,
            String password,
            Function<T, R> convertRequestToModel,
            Function<R, Optional<User>> getOptionalUser
    ) {
        R userInput = convertRequestToModel.apply(signInRequest);
        Optional<User> optionalUser = getOptionalUser.apply(userInput);
        if (optionalUser.isEmpty()) {
            throw new RestException(HttpStatus.UNAUTHORIZED, ErrorResponse.from(ErrorCode.INVALID_CREDENTIALS));
        }
        User user = optionalUser.get();
        // Check for Entity Exists or not
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RestException(HttpStatus.UNAUTHORIZED, ErrorResponse.from(ErrorCode.INVALID_CREDENTIALS));
        }
        JwtPayload jwtPayload = userTransformer.convertUserToJwtPayload(user);
        String jwtToken = jwtAuthenticationManager.createJwtToken(jwtPayload);
        return userTransformer.convertUserToSignInResponse(jwtToken);
    }
}
