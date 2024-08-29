package com.example.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class SecurityConfiguration {

//    private final UserDetailsService userDetailsService;

//    public SecurityConfiguration(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }

//    @Bean
//    public AuthenticationManager customAuthenticationManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        //@formatter:off
//        authenticationManagerBuilder.userDetailsService(userDetailsService)
//                .passwordEncoder(bCryptPasswordEncoder());
//        //@formatter:on
//        return authenticationManagerBuilder.build();
//    }

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
//        http
//                .securityMatcher("/api/**")
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().permitAll()
//                )
//                .csrf((csrf) -> csrf.disable());
//        return http.build();
//    }
}
