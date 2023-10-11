package com.ms.rating.service.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.server.WebFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{

        security
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .anyRequest()
                            .authenticated();
                })
                .oauth2ResourceServer(oauth2-> {
                    oauth2.jwt(Customizer.withDefaults());
                });

        return security.build();
    }
}
