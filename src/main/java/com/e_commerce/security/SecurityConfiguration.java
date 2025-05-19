package com.e_commerce.security;

import com.e_commerce.util.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                configurer -> configurer
                        .requestMatchers(
                                "/api/v1/products/create",
                                "/api/v1/products/update/*",
                                "/api/v1/products/delete/*",
                                "/api/v1/categories/create",
                                "/api/v1/categories/update/*",
                                "/api/v1/categories/delete/*"
                        )
                        .hasRole("ADMIN")
                        .requestMatchers("/api/v1/products/all",
                                "/api/v1/products/all",
                                "/api/v1/products/*",
                                "/api/v1/categories/all",
                                "/api/v1/categories/*",
                                "/api/v1/cart/**" )
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers("user/register", "user/login").permitAll()
                        .anyRequest().authenticated()
        )
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
