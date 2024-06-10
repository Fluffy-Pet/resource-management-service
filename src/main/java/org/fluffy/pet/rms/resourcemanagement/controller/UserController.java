package org.fluffy.pet.rms.resourcemanagement.controller;

import jakarta.validation.Valid;
import org.fluffy.pet.rms.resourcemanagement.dto.request.user.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.user.SignInResponse;
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

    @PostMapping("/signup/mobile")
    public ResponseEntity<ResponseWrapper<SignInResponse>> signupViaMobile(
            @RequestBody @Valid SignupViaMobileRequest signupViaMobileRequest
    ) {
        SignInResponse signInResponse = userService.signup(signupViaMobileRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.success(signInResponse));
    }

    @PostMapping("/signup/email")
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

    @PutMapping("/update-credentials")
    public ResponseEntity<ResponseWrapper<Void>> updateCredentials(
            @RequestBody @Valid UpdatePasswordRequest updatePasswordRequest
    ) {
        userService.updatePassword(updatePasswordRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(null));
    }
}
