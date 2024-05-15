package org.fluffy.pet.rms.resourcemanagement.exception;

public class AppException extends RuntimeException {

    protected AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
