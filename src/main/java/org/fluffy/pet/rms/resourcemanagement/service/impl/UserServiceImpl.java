package org.fluffy.pet.rms.resourcemanagement.service.impl;

import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import lombok.extern.slf4j.Slf4j;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignupInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignupOutput;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.UserRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.user.UserResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.model.User;
import org.fluffy.pet.rms.resourcemanagement.repository.UserRepository;
import org.fluffy.pet.rms.resourcemanagement.service.UserService;
import org.fluffy.pet.rms.resourcemanagement.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserTransformer userTransformer;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,UserTransformer userTransformer){
        this.userRepository=userRepository;
        this.userTransformer=userTransformer;
    }


    @Override
    public SignupOutput createUser(SignupInput createUserRequest) {
        User user = userTransformer.convertSignupInputToModel(createUserRequest);
        try {
            userRepository.save(user);
            return userTransformer.convertUserToSignupOutput(userTransformer.convertUserToJwtPayload(user).toString());
        } catch (ConditionalCheckFailedException e) {
            log.error(
                    String.format("Exception happened in creating user for %s", createUserRequest.mobileInput()), e
            );
            throw new RestException(HttpStatus.BAD_REQUEST, ErrorResponse.from(ErrorCode.DUPLICATE_INFRASTRUCTURE));
        }
    }

    @Override
    public UserResponse getUser(String id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.USER_NOT_FOUND))
        );
        return userTransformer.convertModelToResponse(user);
    }

    @Override
    public UserResponse updateUser(UserRequest updateUserRequest, String id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.USER_NOT_FOUND))
        );
        userTransformer.updateUser(user, updateUserRequest);
        userRepository.save(user);
        return userTransformer.convertModelToResponse(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
