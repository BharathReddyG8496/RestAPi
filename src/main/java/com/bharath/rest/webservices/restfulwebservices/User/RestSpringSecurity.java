package com.bharath.rest.webservices.restfulwebservices.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class RestSpringSecurity {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth-> auth.anyRequest().authenticated());
        http.httpBasic(withDefaults());
        http.csrf(csrf->csrf.disable());
        return http.build();
    }
}