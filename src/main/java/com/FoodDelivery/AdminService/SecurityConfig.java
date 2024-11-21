package com.FoodDelivery.AdminService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disable CSRF for development/testing
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login","/api/auth/secured","/users","/users/{id}","/users/Registration","/user/restaurant","/user/orders/{userId}","/user/orders").permitAll()
                        // Allow login endpoint
                        .anyRequest().authenticated() // Secure other endpoints
                );
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable() // Disable CSRF for development/testing
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/auth/login","/api/auth/secured").permitAll()
//                        // Allow login endpoint
//                        .anyRequest().authenticated() // Secure other endpoints
//                );
//        return http.build();
//    }
}