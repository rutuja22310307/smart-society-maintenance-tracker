package com.society.maintenance.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // Public APIs
                        .requestMatchers(
                                "/api/auth/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // ==========================
                        // USER APIs
                        // ==========================
                        .requestMatchers("/api/users/**")
                        .hasRole("ADMIN")

                        // ==========================
                        // NOTICE APIs
                        // ==========================
                        .requestMatchers(HttpMethod.POST, "/api/notices/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/api/notices/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/notices/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/notices/**")
                        .hasAnyRole("ADMIN", "STAFF", "RESIDENT")

                        // ==========================
                        // COMPLAINT APIs
                        // ==========================
                        .requestMatchers(HttpMethod.POST, "/api/complaints/**")
                        .hasRole("RESIDENT")

                        .requestMatchers(HttpMethod.PUT, "/api/complaints/**")
                        .hasAnyRole("ADMIN", "STAFF")

                        .requestMatchers(HttpMethod.GET, "/api/complaints/**")
                        .authenticated()

                        .requestMatchers(HttpMethod.DELETE, "/api/complaints/**")
                        .hasRole("ADMIN")
                      .requestMatchers(HttpMethod.GET, "/api/dashboard/**")
                        .hasAnyRole("ADMIN", "STAFF", "RESIDENT")
                        // ==========================
                        // MAINTENANCE APIs
                        // ==========================
                        .requestMatchers(HttpMethod.PUT, "/api/maintenance/**")
                        .hasAnyRole("ADMIN", "STAFF")

                        .requestMatchers(HttpMethod.GET, "/api/maintenance/**")
                        .authenticated()

                        .requestMatchers(HttpMethod.POST, "/api/maintenance/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/maintenance/**")
                        .hasRole("ADMIN")

                        // ==========================
                        // COMPLAINT VOTING
                        // ==========================
                        .requestMatchers("/api/votes/**")
                        .hasRole("RESIDENT")
                    .requestMatchers("/api/dashboard/**")
                    .hasAnyRole("ADMIN", "STAFF", "RESIDENT")
                        // Any other API
                        .anyRequest()
                        .authenticated()
                )

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}