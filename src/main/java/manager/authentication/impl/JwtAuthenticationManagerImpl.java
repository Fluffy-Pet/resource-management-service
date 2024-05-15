package manager.authentication.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import manager.authentication.JwtAuthenticationManager;
import manager.authentication.configurations.JwtAuthenticationManagerConfiguration;
import manager.authentication.enums.JwtAuthenticationErrorCause;
import manager.authentication.exceptions.JwtAuthenticationManagerException;
import manager.authentication.models.JwtPayload;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class JwtAuthenticationManagerImpl implements JwtAuthenticationManager {
    private final JWTVerifier jwtVerifier;

    private final String tokenIssuer;

    private final Long tokenValidityDurationMillis;

    private final Algorithm algorithm;

    public JwtAuthenticationManagerImpl(
            JwtAuthenticationManagerConfiguration jwtAuthenticationManagerConfiguration
    ) {
        this.algorithm = jwtAuthenticationManagerConfiguration.authenticationAlgorithm().getAlgorithm();
        this.tokenIssuer = jwtAuthenticationManagerConfiguration.tokenIssuer();
        this.tokenValidityDurationMillis = jwtAuthenticationManagerConfiguration.tokenValidityDurationMillis();
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

    @Override
    public String createJwtToken(JwtPayload jwtPayload) {
        long currentTimestamp = Instant.now().toEpochMilli();
        long expiresAtTimestamp = currentTimestamp + tokenValidityDurationMillis;
        JWTCreator.Builder jwtBuilder = JWT.create()
                .withIssuer(tokenIssuer)
                .withSubject(jwtPayload.getSub())
                .withIssuedAt(new Date(currentTimestamp))
                .withExpiresAt(new Date(expiresAtTimestamp))
                .withJWTId(UUID.randomUUID().toString());
        return jwtBuilder.sign(algorithm);
    }
}
