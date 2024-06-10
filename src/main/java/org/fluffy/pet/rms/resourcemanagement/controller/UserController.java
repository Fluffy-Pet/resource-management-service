package org.fluffy.pet.rms.resourcemanagement.controller;

import jakarta.validation.Valid;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.user.SignInResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.user.UserResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ResponseWrapper;
import org.fluffy.pet.rms.resourcemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public <T> ResponseEntity<ResponseWrapper<UserResponse<T>>> getCurrentUser() {
        UserResponse<T> userResponse = userService.getCurrentUser();
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(userResponse));
    }

    @PostMapping("/sign-up/mobile")
    public ResponseEntity<ResponseWrapper<SignInResponse>> signupViaMobile(
            @RequestBody @Valid SignupViaMobileRequest signupViaMobileRequest
    ) {
        SignInResponse signInResponse = userService.signup(signupViaMobileRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.success(signInResponse));
    }

    @PostMapping("/sign-up/email")
    public ResponseEntity<ResponseWrapper<SignInResponse>> signupViaEmail(
            @RequestBody @Valid SignupViaEmailRequest signupViaEmailRequest
    ) {
        SignInResponse signInResponse = userService.signup(signupViaEmailRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.success(signInResponse));
    }

    @PostMapping("/sign-in/mobile")
    public ResponseEntity<ResponseWrapper<SignInResponse>> signInViaMobile(
            @RequestBody @Valid SignInViaMobileRequest signInViaMobileRequest
    ) {
        SignInResponse signInResponse = userService.signIn(signInViaMobileRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(signInResponse));
    }

    @PostMapping("/sign-in/email")
    public ResponseEntity<ResponseWrapper<SignInResponse>> signInViaEmail(
            @RequestBody @Valid SignInViaEmailRequest signInViaEmailRequest
    ) {
        SignInResponse signInResponse = userService.signIn(signInViaEmailRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(signInResponse));
    }

    @PutMapping("/update-password")
    public ResponseEntity<ResponseWrapper<Void>> updatePassword(
            @RequestBody @Valid UpdatePasswordRequest updatePasswordRequest
    ) {
        userService.updateCredentials(updatePasswordRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(null));
    }

    @PutMapping("/update-email")
    public ResponseEntity<ResponseWrapper<Void>> updateEmail(
            @RequestBody @Valid UpdateEmailRequest updateEmailRequest
    ) {
        userService.updateCredentials(updateEmailRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(null));
    }

    @PutMapping("/update-mobile")
    public ResponseEntity<ResponseWrapper<Void>> updateMobile(
            @RequestBody @Valid UpdateMobileRequest updateMobileRequest
    ) {
        userService.updateCredentials(updateMobileRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(null));
    }
}
