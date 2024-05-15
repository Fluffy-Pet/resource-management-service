package manager.authentication.algorithms.impl;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.SneakyThrows;
import manager.authentication.algorithms.AuthenticationAlgorithm;
import manager.authentication.constants.Constants;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaAuthenticationAlgorithm implements AuthenticationAlgorithm {

    private final String rsaPublicKey;

    public RsaAuthenticationAlgorithm(String rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }

    @Override
    public Algorithm getAlgorithm() {
        return Algorithm.RSA256(
                getPublicKey(this.rsaPublicKey),
                null
        );
    }

    @SneakyThrows
    private RSAPublicKey getPublicKey(String rsaPublicKey) {
        byte[] keyBytes = Base64.getDecoder().decode(rsaPublicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(Constants.RSA);
        return (RSAPublicKey) keyFactory.generatePublic(spec);
    }
}
