package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())   // CSRF disable (403 fix)
            .authorizeHttpRequests(auth-> auth 
            		.requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()  // sab APIs open
            );

        return http.build();
    }
    
}
