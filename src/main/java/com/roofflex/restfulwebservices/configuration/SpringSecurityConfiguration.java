package com.roofflex.restfulwebservices.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        // all requests should be authenticated
        httpSecurity.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        // if a request is not authenticated, a pop-up with auth is shown (basic auth)
        httpSecurity.httpBasic(Customizer.withDefaults());

        httpSecurity.csrf().disable();

        return httpSecurity.build();
    }
}
