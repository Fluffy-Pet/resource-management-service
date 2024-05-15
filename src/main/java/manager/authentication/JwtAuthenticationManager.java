package manager.authentication;

import com.auth0.jwt.interfaces.DecodedJWT;
import manager.authentication.exceptions.JwtAuthenticationManagerException;
import manager.authentication.models.JwtPayload;

public interface JwtAuthenticationManager {

    JwtPayload verifyAndDecodeToken(String token) throws JwtAuthenticationManagerException;

    JwtPayload convertDecodedJwtTokenToJwtPayload(DecodedJWT decodedJWT);
}
