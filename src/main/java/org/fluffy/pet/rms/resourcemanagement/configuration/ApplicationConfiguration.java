package org.fluffy.pet.rms.resourcemanagement.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.ServletContext;
import manager.authentication.algorithms.AuthenticationAlgorithm;
import manager.authentication.algorithms.impl.RsaAuthenticationAlgorithm;
import manager.authentication.configurations.JwtAuthenticationManagerConfiguration;
import manager.authentication.impl.JwtAuthenticationManager;
import org.fluffy.pet.rms.resourcemanagement.configuration.contexts.RequestContext;
import org.fluffy.pet.rms.resourcemanagement.configuration.contexts.UserContext;
import org.fluffy.pet.rms.resourcemanagement.configuration.properties.AwsProperties;
import org.fluffy.pet.rms.resourcemanagement.configuration.properties.BCryptProperties;
import org.fluffy.pet.rms.resourcemanagement.configuration.properties.JwtTokenProperties;
import org.fluffy.pet.rms.resourcemanagement.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.security.SecureRandom;
import java.util.List;

@Configuration
@EnableWebMvc
public class ApplicationConfiguration {
    @Bean
    public JsonMapper getJsonMapper() {
        return JsonMapper.builder()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .findAndAddModules()
                .build();
    }

    @Bean
    public JwtAuthenticationManager getJwtAuthenticationManager(
            JwtTokenProperties jwtTokenProperties
    ) {
        AuthenticationAlgorithm authenticationAlgorithm = new RsaAuthenticationAlgorithm(
                jwtTokenProperties.getRsaPublicKey(),
                jwtTokenProperties.getRsaPrivateKey()
        );
        JwtAuthenticationManagerConfiguration jwtAuthenticationManagerConfiguration = new JwtAuthenticationManagerConfiguration(
                authenticationAlgorithm, jwtTokenProperties.getTokenIssuer(), jwtTokenProperties.getTokenValidityDurationMillis()
        );
        return new JwtAuthenticationManager(jwtAuthenticationManagerConfiguration);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(BCryptProperties bCryptProperties) {
        return new BCryptPasswordEncoder(bCryptProperties.getStrength(), new SecureRandom());
    }

    @Bean
    public RequestContext getRequestContext() {
        return new RequestContext();
    }

    @Bean
    public UserContext getUserContext() {
        return new UserContext();
    }

    @Bean
    public OpenAPI openAPI(ServletContext servletContext) {
        Server server = new Server().url(servletContext.getContextPath());
        return new OpenAPI()
                .servers(List.of(server))
                .addSecurityItem(new SecurityRequirement().addList(Constants.BEARER_AUTH))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        Constants.BEARER_AUTH,
                                        new SecurityScheme()
                                                .name(Constants.BEARER_AUTH)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                .info(new Info().title("Fluffy Pet").version("1.0").description("API Documentation"));
    }

    @Bean
    public S3Presigner getS3Presigner(@Autowired AwsProperties awsProperties) {
        return S3Presigner.builder()
                .region(Region.of(awsProperties.getRegion()))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        awsProperties.getAccessKeyId(),
                                        awsProperties.getSecretAccessKey()
                                )
                        )
                )
                .build();
    }
}
