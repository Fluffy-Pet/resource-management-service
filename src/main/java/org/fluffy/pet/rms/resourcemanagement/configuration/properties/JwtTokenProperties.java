package org.fluffy.pet.rms.resourcemanagement.configuration.properties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fluffy.pet.rms.resourcemanagement.annotations.ApplicationProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ApplicationProperties
@ConfigurationProperties(prefix = "org.fluffy.pet.jwt")
@Getter
@Setter
@NoArgsConstructor
@Validated
public class JwtTokenProperties {
    @NotBlank
    private String tokenIssuer;

    @NotNull
    private Long tokenValidityDurationMillis;

    @NotBlank
    private String rsaPublicKey;

    @NotBlank
    private String rsaPrivateKey;
}
