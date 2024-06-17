package org.fluffy.pet.rms.resourcemanagement.configuration.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "org.fluffy.pet.aws.credentials")
@Getter
@Setter
@NoArgsConstructor
@Validated
public class AwsProperties {
    @NotBlank
    private String accessKeyId;

    @NotBlank
    private String secretAccessKey;

    @NotBlank
    private String region;

    @NotBlank
    private String bucketName;
}
