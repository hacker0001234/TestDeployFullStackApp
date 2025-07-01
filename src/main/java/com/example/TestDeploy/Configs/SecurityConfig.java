package com.example.TestDeploy.Configs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;


@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/api/authentication/check").permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2Login(oauth2LoginSpec -> oauth2LoginSpec
                        .loginPage("/oauth2/authorization/google")
                        .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/"))
                        .authenticationFailureHandler(new RedirectServerAuthenticationFailureHandler("/error"))
                )
                .build();
    }
}
