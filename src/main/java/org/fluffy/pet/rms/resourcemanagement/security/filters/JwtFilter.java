package org.fluffy.pet.rms.resourcemanagement.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import manager.authentication.JwtAuthenticationManager;
import manager.authentication.models.JwtPayload;
import org.fluffy.pet.rms.resourcemanagement.configuration.contexts.UserContext;
import org.fluffy.pet.rms.resourcemanagement.util.CommonUtils;
import org.fluffy.pet.rms.resourcemanagement.util.HeaderConstants;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtAuthenticationManager jwtAuthenticationManager;

    private final UserContext userContext;

    public JwtFilter(
            JwtAuthenticationManager jwtAuthenticationManager,
            UserContext userContext
    ) {
        this.jwtAuthenticationManager = jwtAuthenticationManager;
        this.userContext = userContext;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String authorizationHeader = request.getHeader(HeaderConstants.AUTHORIZATION);
            if (authorizationHeader != null && (CommonUtils.isBearerToken(authorizationHeader))) {
                String token = authorizationHeader.substring(HeaderConstants.BEARER_SPACE.length());
                JwtPayload jwtPayload = jwtAuthenticationManager.verifyAndDecodeToken(token);
                // TODO: Fetch User with this ID to verify if it exists or not.
                setupUserContext(userContext, jwtPayload);
                List<SimpleGrantedAuthority> grantedAuthorities = List.of();
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        jwtPayload.getSub(), null, grantedAuthorities
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        } catch (Exception ignored) {
            // Ignore Exception
        }
        filterChain.doFilter(request, response);
    }

    private void setupUserContext(UserContext userContext, JwtPayload jwtPayload) {
        userContext.setUserId(jwtPayload.getSub());
        userContext.setUserType(jwtPayload.getUserType());
    }

}
