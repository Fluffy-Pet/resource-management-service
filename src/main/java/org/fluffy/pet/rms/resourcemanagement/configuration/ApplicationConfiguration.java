package org.fluffy.pet.rms.resourcemanagement.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import manager.authentication.JwtAuthenticationManager;
import manager.authentication.algorithms.AuthenticationAlgorithm;
import manager.authentication.algorithms.impl.RsaAuthenticationAlgorithm;
import manager.authentication.configurations.JwtAuthenticationManagerConfiguration;
import manager.authentication.impl.JwtAuthenticationManagerImpl;
import org.fluffy.pet.rms.resourcemanagement.configuration.contexts.RequestContext;
import org.fluffy.pet.rms.resourcemanagement.configuration.contexts.UserContext;
import org.fluffy.pet.rms.resourcemanagement.configuration.properties.JwtTokenProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableMongoAuditing
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
                jwtTokenProperties.getRsaPublicKey()
        );
        JwtAuthenticationManagerConfiguration jwtAuthenticationManagerConfiguration = new JwtAuthenticationManagerConfiguration(
                authenticationAlgorithm, jwtTokenProperties.getTokenIssuer(), jwtTokenProperties.getTokenValidityDurationMillis()
        );
        return new JwtAuthenticationManagerImpl(jwtAuthenticationManagerConfiguration);
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
