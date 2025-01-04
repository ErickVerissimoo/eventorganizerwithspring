package com.eventorganizerspring.eventorganizer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.eventorganizerspring.eventorganizer.filter.AuthenticatorFilter;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    @Setter @Lazy @Autowired
    private UserDetailsService service;
@Bean
public SecurityFilterChain security(HttpSecurity http) throws Throwable{
    http.addFilterBefore(new AuthenticatorFilter(), UsernamePasswordAuthenticationFilter.class) .authorizeHttpRequests(c -> c.requestMatchers("/public/**").permitAll().anyRequest().authenticated()).csrf(c-> c.disable())  .userDetailsService(service);

    return http.build();
}
@Bean
public AuthenticationManager manager(HttpSecurity http) throws Throwable{
AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
return builder.build(); 
}
@Bean

public PasswordEncoder encoder(){
    return new BCryptPasswordEncoder();
}

}
