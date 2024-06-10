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
    DUPLICATE_USER("006", "Duplicate User"),
    USER_NOT_FOUND("007", "User not found"),
    UN_AUTHORISED("008", "Un Authorised Action"),
    DUPLICATE_INFRASTRUCTURE("009", "Infrastructure already exists"),
    INFRASTRUCTURE_NOT_FOUND("010", "Infrastructure not found"),
    DOCTOR_ALREADY_EXISTS("010","Doctor already exist" ),
    DOCTOR_NOT_FOUND("011","Doctor not found" ),
    VOLUNTEER_NOT_FOUND("012","Volunteer not found" ),
    CLINIC_ALREADY_EXISTS("013","Clinic already exist" ),
    CLINIC_NOT_FOUND("014","Clinic not found" ),
    VOLUNTEER_ALREADY_EXISTS("015","Volunteer already exist" ),
    INVALID_CREDENTIALS("016", "Invalid Credentials"),
    INSUFFICIENT_PERMISSION("017", "Insufficient Permission for Action");

    private final String code;

    private final String message;

    ErrorCode(String code, String message) {
        this.code = Constants.ERROR_CODE_PREFIX + code;
        this.message = message;
    }
}
