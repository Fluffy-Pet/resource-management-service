package org.fluffy.pet.rms.resourcemanagement.configuration.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fluffy.pet.rms.resourcemanagement.annotations.ApplicationProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ApplicationProperties
@ConfigurationProperties(prefix = "org.fluffy.pet.jwt")
@Getter
@Setter
@NoArgsConstructor
public class JwtTokenProperties {
    private String tokenIssuer;

    private String rsaPublicKey;
}
