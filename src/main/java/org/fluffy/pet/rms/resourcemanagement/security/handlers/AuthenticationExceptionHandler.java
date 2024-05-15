package org.fluffy.pet.rms.resourcemanagement.security.handlers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import manager.authentication.JwtAuthenticationManager;
import manager.authentication.exceptions.JwtAuthenticationManagerException;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ResponseWrapper;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.util.CommonUtils;
import org.fluffy.pet.rms.resourcemanagement.util.HeaderConstants;
import org.fluffy.pet.rms.resourcemanagement.util.MessageConstants;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class AuthenticationExceptionHandler implements AuthenticationEntryPoint {

    private final JsonMapper jsonMapper;

    private final JwtAuthenticationManager jwtAuthenticationManager;

    public AuthenticationExceptionHandler(
            JsonMapper jsonMapper,
            JwtAuthenticationManager jwtAuthenticationManager
    ) {
        this.jsonMapper = jsonMapper;
        this.jwtAuthenticationManager = jwtAuthenticationManager;
    }

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        if (response.isCommitted()) {
            return;
        }
        String authorizationHeader = request.getHeader(HeaderConstants.AUTHORIZATION);
        if (authorizationHeader == null) {
            ErrorResponse errorResponse = ErrorResponse.from(ErrorCode.INPUT_VALIDATION_ERROR);
            errorResponse.setDetail(MessageConstants.AUTHORIZATION_HEADER_MUST_BE_PRESENT);
            CommonUtils.writeResponse(
                    response,
                    HttpStatus.UNAUTHORIZED,
                    ResponseWrapper.failure(List.of(errorResponse)),
                    jsonMapper
            );
            return;
        }

        if (!CommonUtils.isBearerToken(authorizationHeader)) {
            ErrorResponse errorResponse = ErrorResponse.from(ErrorCode.TOKEN_MUST_START_WITH_BEARER);
            CommonUtils.writeResponse(
                    response,
                    HttpStatus.UNAUTHORIZED,
                    ResponseWrapper.failure(List.of(errorResponse)),
                    jsonMapper
            );
            return;
        }

        try {
            jwtAuthenticationManager.verifyAndDecodeToken(authorizationHeader);
        } catch (JwtAuthenticationManagerException e) {
            ErrorCode errorCode = switch (e.getErrorCause()) {
                case JWT_TOKEN_DECODING_FAILED -> ErrorCode.INVALID_JWT_TOKEN;
                case JWT_TOKEN_EXPIRED -> ErrorCode.JWT_TOKEN_EXPIRED;
            };
            ErrorResponse errorResponse = ErrorResponse.from(errorCode);
            CommonUtils.writeResponse(
                    response,
                    HttpStatus.UNAUTHORIZED,
                    ResponseWrapper.failure(List.of(errorResponse)),
                    jsonMapper
            );
        }
    }
}
