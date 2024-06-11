package org.fluffy.pet.rms.resourcemanagement.configuration.properties;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "org.fluffy.pet.bcrypt")
@Getter
@Setter
@NoArgsConstructor
@Validated
public class BCryptProperties {
    @NotNull
    private Integer strength;
}
