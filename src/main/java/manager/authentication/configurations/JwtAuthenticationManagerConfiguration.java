package manager.authentication.configurations;


import manager.authentication.algorithms.AuthenticationAlgorithm;

public record JwtAuthenticationManagerConfiguration(
        AuthenticationAlgorithm authenticationAlgorithm,
        String tokenIssuer
) {
}
