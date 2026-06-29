package org.example.internship_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * DEVELOPMENT security configuration.
 *
 * <p>Spring Security locks every endpoint by default (the random password you see
 * in the console). While we build the features we don't want that, so this config
 * opens all endpoints — no login required. This lets every teammate test their
 * controllers freely.
 *
 * <p>⚠️ This is for development only. At the end of the project, replace the
 * "permit all" rule below with real authentication: login/register endpoints,
 * BCrypt-hashed passwords, and role-based access (STUDENT vs COMPANY).
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * The "rulebook" Spring Security follows for every incoming request.
     * Right now it allows everything.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF protection blocks simple POST/PUT tests from Postman.
                // It is not needed for a stateless REST API, so we turn it off.
                .csrf(csrf -> csrf.disable())
                // Allow every request without authentication (development only).
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }

    /**
     * Tool that hashes passwords with BCrypt. We expose it now so the login/register
     * feature can use it later to store passwords safely (never in plain text).
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
