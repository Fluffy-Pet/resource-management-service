package manager.authentication.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import manager.authentication.JwtAuthenticationManager;
import manager.authentication.configurations.JwtAuthenticationManagerConfiguration;
import manager.authentication.enums.JwtAuthenticationErrorCause;
import manager.authentication.exceptions.JwtAuthenticationManagerException;
import manager.authentication.models.JwtPayload;

public class JwtAuthenticationManagerImpl implements JwtAuthenticationManager {
    private final JWTVerifier jwtVerifier;

    public JwtAuthenticationManagerImpl(
            JwtAuthenticationManagerConfiguration jwtAuthenticationManagerConfiguration
    ) {
        Algorithm algorithm = jwtAuthenticationManagerConfiguration.authenticationAlgorithm().getAlgorithm();
        String tokenIssuer = jwtAuthenticationManagerConfiguration.tokenIssuer();
        this.jwtVerifier = JWT.require(algorithm).withIssuer(tokenIssuer).build();
    }

    @Override
    public JwtPayload verifyAndDecodeToken(String token) throws JwtAuthenticationManagerException {
        try {
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return convertDecodedJwtTokenToJwtPayload(decodedJWT);
        } catch (TokenExpiredException tokenExpiredException) {
            throw new JwtAuthenticationManagerException(JwtAuthenticationErrorCause.JWT_TOKEN_EXPIRED);
        } catch (Exception e) {
            throw new JwtAuthenticationManagerException(JwtAuthenticationErrorCause.JWT_TOKEN_DECODING_FAILED);
        }
    }

    @Override
    public JwtPayload convertDecodedJwtTokenToJwtPayload(DecodedJWT decodedJWT) {
        return JwtPayload
                .builder()
                .sub(decodedJWT.getSubject())
                .build();
    }
}
