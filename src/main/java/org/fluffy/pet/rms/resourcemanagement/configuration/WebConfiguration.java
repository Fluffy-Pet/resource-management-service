package org.fluffy.pet.rms.resourcemanagement.configuration;

import org.fluffy.pet.rms.resourcemanagement.security.interceptor.AccessCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final AccessCheckInterceptor accessCheckInterceptor;

    @Autowired
    public WebConfiguration(AccessCheckInterceptor accessCheckInterceptor) {
        this.accessCheckInterceptor = accessCheckInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessCheckInterceptor);
    }
}

