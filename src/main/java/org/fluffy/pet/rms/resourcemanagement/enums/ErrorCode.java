package org.fluffy.pet.rms.resourcemanagement.enums;

import lombok.Getter;
import lombok.ToString;
import org.fluffy.pet.rms.resourcemanagement.util.Constants;

@Getter
@ToString
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("001", "Internal Server Error"),
    INPUT_VALIDATION_ERROR("002", "Input Validation Error"),
    INVALID_JWT_TOKEN("003", "Invalid JWT token provided"),
    JWT_TOKEN_EXPIRED("004", "JWT token expired"),
    TOKEN_MUST_START_WITH_BEARER("005", "Token must start with Bearer"),
    ;

    private final String code;

    private final String message;

    ErrorCode(String code, String message) {
        this.code = Constants.ERROR_CODE_PREFIX + code;
        this.message = message;
    }
}
