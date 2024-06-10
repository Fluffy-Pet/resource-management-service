package org.fluffy.pet.rms.resourcemanagement.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import manager.authentication.algorithms.AuthenticationAlgorithm;
import manager.authentication.algorithms.impl.RsaAuthenticationAlgorithm;
import manager.authentication.configurations.JwtAuthenticationManagerConfiguration;
import manager.authentication.impl.JwtAuthenticationManager;
import org.fluffy.pet.rms.resourcemanagement.configuration.contexts.RequestContext;
import org.fluffy.pet.rms.resourcemanagement.configuration.contexts.UserContext;
import org.fluffy.pet.rms.resourcemanagement.configuration.properties.JwtTokenProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
    public RequestContext getRequestContext() {
        return new RequestContext();
    }

    @Bean
    public UserContext getUserContext() {
        return new UserContext();
    }
}
