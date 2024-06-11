package org.fluffy.pet.rms.resourcemanagement.security.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.fluffy.pet.rms.resourcemanagement.annotations.CheckAccess;
import org.fluffy.pet.rms.resourcemanagement.configuration.contexts.UserContext;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class AccessCheckInterceptor implements HandlerInterceptor {
    private final UserContext userContext;

    @Autowired
    public AccessCheckInterceptor(UserContext userContext) {
        this.userContext = userContext;
    }

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler
    ) {
        if (handler instanceof HandlerMethod handlerMethod) {
            Method method = handlerMethod.getMethod();
            CheckAccess checkAccess = method.getAnnotation(CheckAccess.class);
            if (checkAccess != null) {
                UserType[] requiredAccessLevels = checkAccess.values();
                UserType userAccessLevel = userContext.getUserType();

                if (!Arrays.asList(requiredAccessLevels).contains(userAccessLevel)) {
                    throw new RestException(HttpStatus.UNAUTHORIZED, ErrorResponse.from(ErrorCode.INSUFFICIENT_PERMISSION));
                }
            }
        }
        return true;
    }
}
