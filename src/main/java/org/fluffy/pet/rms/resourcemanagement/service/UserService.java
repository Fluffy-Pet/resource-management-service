package org.fluffy.pet.rms.resourcemanagement.service;

import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignupInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignupOutput;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.UserRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.user.UserResponse;

public interface UserService {
    SignupOutput createUser(SignupInput createUserRequest);

    UserResponse getUser(String id);

    UserResponse updateUser(UserRequest updateUserRequest, String id);

    void deleteUser(String id);
}
