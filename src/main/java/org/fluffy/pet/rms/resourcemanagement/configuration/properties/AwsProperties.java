package org.fluffy.pet.rms.resourcemanagement.configuration.properties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fluffy.pet.rms.resourcemanagement.annotations.ApplicationProperties;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ApplicationProperties
@ConfigurationProperties(prefix = "org.fluffy.pet.aws")
@Getter
@Setter
@NoArgsConstructor
@Validated
public class AwsProperties {
    @NotNull
    @URL
    private String dynamoDbEndPoint;

    @NotBlank
    private String accessKey;

    @NotBlank
    private String secretKey;

    @NotBlank
    private String region;
}
