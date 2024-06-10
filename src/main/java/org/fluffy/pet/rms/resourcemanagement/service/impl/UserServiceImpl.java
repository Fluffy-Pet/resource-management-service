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
import org.fluffy.pet.rms.resourcemanagement.helper.DoctorHelper;
import org.fluffy.pet.rms.resourcemanagement.helper.VolunteerHelper;
import org.fluffy.pet.rms.resourcemanagement.model.User;
import org.fluffy.pet.rms.resourcemanagement.repository.UserRepository;
import org.fluffy.pet.rms.resourcemanagement.service.UserService;
import org.fluffy.pet.rms.resourcemanagement.transformer.UserTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.Result;
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

    private final DoctorHelper doctorHelper;

    private final VolunteerHelper volunteerHelper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserTransformer userTransformer, PasswordEncoder passwordEncoder, JwtAuthenticationManager jwtAuthenticationManager, DoctorHelper doctorHelper, VolunteerHelper volunteerHelper) {
        this.userRepository = userRepository;
        this.userTransformer = userTransformer;
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthenticationManager = jwtAuthenticationManager;
        this.doctorHelper = doctorHelper;
        this.volunteerHelper = volunteerHelper;
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
                signInViaMobileRequest.getUserType(),
                userTransformer::convertMobileRequestToModel,
                userRepository::findUserByMobile
        );
    }

    @Override
    public SignInResponse signInViaMobile(SignInViaEmailRequest signInViaMobileRequest) {
        return signInUserOrThrowException(
                signInViaMobileRequest.getUserEmailRequest(),
                signInViaMobileRequest.getPassword(),
                signInViaMobileRequest.getUserType(),
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
            if (checkIfEntityExists(user.getId(), userType)) {
                throw new RestException(HttpStatus.BAD_REQUEST, ErrorResponse.from(ErrorCode.DUPLICATE_USER));
            }
            User createdUser = userRepository.save(user);
            Result<Void, ErrorCode> entityCreationStatus = createEntity(createdUser.getId(), userType);
            if (entityCreationStatus.isFailure()) {
                throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorResponse.from(entityCreationStatus.getError()));
            }
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
            UserType userType,
            Function<T, R> convertRequestToModel,
            Function<R, Optional<User>> getOptionalUser
    ) {
        R userInput = convertRequestToModel.apply(signInRequest);
        Optional<User> optionalUser = getOptionalUser.apply(userInput);
        if (optionalUser.isEmpty()) {
            throw new RestException(HttpStatus.UNAUTHORIZED, ErrorResponse.from(ErrorCode.INVALID_CREDENTIALS));
        }
        User user = optionalUser.get();
        boolean entityExists = checkIfEntityExists(user.getId(), userType);
        if (!entityExists) {
            throw new RestException(HttpStatus.UNAUTHORIZED, ErrorResponse.from(ErrorCode.INVALID_CREDENTIALS));
        }
        // Check for Entity Exists or not
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RestException(HttpStatus.UNAUTHORIZED, ErrorResponse.from(ErrorCode.INVALID_CREDENTIALS));
        }
        JwtPayload jwtPayload = userTransformer.convertUserToJwtPayload(user);
        String jwtToken = jwtAuthenticationManager.createJwtToken(jwtPayload);
        return userTransformer.convertUserToSignInResponse(jwtToken);
    }

    private Result<Void, ErrorCode> createEntity(String userId, UserType userType) {
        return switch (userType) {
            case DOCTOR -> volunteerHelper.createUserEntityForIdOnly(userId);
            case VOLUNTEER -> doctorHelper.createUserEntityForIdOnly(userId);
            case CLIENT -> Result.success(null);
        };
    }

    private boolean checkIfEntityExists(String userId, UserType userType) {
        return switch (userType) {
            case VOLUNTEER -> volunteerHelper.checkUserEntityExists(userId);
            case DOCTOR -> doctorHelper.checkUserEntityExists(userId);
            case CLIENT -> false;
        };
    }
}
