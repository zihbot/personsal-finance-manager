package com.zihbot.pfm.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor()
public class WebSecurityConfig {

    private final List<PfmAuthFilter> authFilters;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Disable CSRF (cross site request forgery)
        http = http
        .csrf()
            .disable()
        .authorizeRequests()
            .antMatchers("/users/login").permitAll()
            .anyRequest().authenticated()
            .and()
        .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and();

        for (var filter : authFilters) {
            http = http.addFilterBefore(filter, BasicAuthenticationFilter.class);
        }

        http.formLogin().disable();
        return http.build();
    }
}
