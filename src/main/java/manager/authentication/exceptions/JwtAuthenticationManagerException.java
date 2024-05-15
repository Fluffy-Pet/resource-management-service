package manager.authentication.exceptions;

import lombok.Getter;
import manager.authentication.enums.JwtAuthenticationErrorCause;

@Getter
public class JwtAuthenticationManagerException extends RuntimeException {
    private final JwtAuthenticationErrorCause errorCause;

    public JwtAuthenticationManagerException(JwtAuthenticationErrorCause jwtAuthenticationErrorCause) {
        this(jwtAuthenticationErrorCause, null);
    }

    public JwtAuthenticationManagerException(JwtAuthenticationErrorCause jwtAuthenticationErrorCause, Throwable cause) {
        super(jwtAuthenticationErrorCause.name(), cause);
        this.errorCause = jwtAuthenticationErrorCause;
    }
}
