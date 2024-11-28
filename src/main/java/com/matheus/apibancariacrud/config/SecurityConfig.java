package com.matheus.apibancariacrud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/docs-apiBancaria/**",
                                "/docs-apiBancaria.html",
                                "/v3/api-docs/**",
                                "/swagger-ui/**"
                        ).permitAll() // Permitir acesso público ao Swagger
                        .anyRequest().authenticated() // Exigir autenticação para outras rotas
                )
                .httpBasic(httpBasic -> httpBasic.realmName("ApiBancaria")); // Configuração correta do HTTP Basic

        return http.build();
    }
}

