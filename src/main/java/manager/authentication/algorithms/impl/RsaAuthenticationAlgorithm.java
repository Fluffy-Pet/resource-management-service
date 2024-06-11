package manager.authentication.algorithms.impl;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.SneakyThrows;
import manager.authentication.algorithms.AuthenticationAlgorithm;
import manager.authentication.constants.Constants;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaAuthenticationAlgorithm implements AuthenticationAlgorithm {

    private final String rsaPublicKey;

    private final String rsaPrivateKey;

    public RsaAuthenticationAlgorithm(String rsaPublicKey, String rsaPrivateKey) {
        this.rsaPublicKey = rsaPublicKey;
        this.rsaPrivateKey = rsaPrivateKey;
    }

    @Override
    public Algorithm getAlgorithm() {
        return Algorithm.RSA256(
                getPublicKey(),
                getPrivateKey()
        );
    }

    @SneakyThrows
    private RSAPublicKey getPublicKey() {
        byte[] keyBytes = Base64.getDecoder().decode(this.rsaPublicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(Constants.RSA);
        return (RSAPublicKey) keyFactory.generatePublic(spec);
    }

    @SneakyThrows
    private RSAPrivateKey getPrivateKey() {
        byte[] keyBytes = Base64.getDecoder().decode(this.rsaPrivateKey);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(Constants.RSA);
        return (RSAPrivateKey) keyFactory.generatePrivate(spec);
    }
}
