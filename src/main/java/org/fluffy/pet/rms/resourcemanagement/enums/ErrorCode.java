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
    INSUFFICIENT_PERMISSION("017", "Insufficient Permission for Action"),
    SHELTER_HOME_NOT_FOUND("018","Shelter Home not found" ),
    SHELTER_ALREADY_EXISTS("019","Shelter already exist" ),
    PET_NOT_FOUND("020", "Pet not found"),
    PET_ALREADY_EXISTS("021", "Pet already exists"),
    ADMIN_NOT_FOUND("022", "Admin not found"),
    CLIENT_NOT_FOUND("023", "Customer not found"),
    SERVICE_NOT_FOUND("024", "FluffyPetService not found"),
    SERVICE_ALREADY_EXISTS("025", "FluffyPetService already exists"),
    BOOKING_NOT_FOUND("026", "Booking not found"),
    BOOKING_ALREADY_EXISTS("027", "Booking already exists"),
    INVALID_FILTER_REQUEST("028", "Invalid Filter Request"),
    PET_OWNER_MIS_MATCH("029", "Pet is not owned by current user"),
    UNKNOWN_TYPE("030", "Unknown enum type");

    private final String code;

    private final String message;

    ErrorCode(String code, String message) {
        this.code = Constants.ERROR_CODE_PREFIX + code;
        this.message = message;
    }
}
