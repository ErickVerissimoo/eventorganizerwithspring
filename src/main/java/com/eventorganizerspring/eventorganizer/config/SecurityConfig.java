package com.eventorganizerspring.eventorganizer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.eventorganizerspring.eventorganizer.filter.AuthenticatorFilter;
import com.eventorganizerspring.eventorganizer.service.JwtServiceImpl;

import lombok.RequiredArgsConstructor;
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    @Lazy @Autowired(required = false)
    private UserDetailsService service;
    private final JwtServiceImpl impl;
   
@Bean
public SecurityFilterChain security(HttpSecurity http) throws Throwable{
    http.addFilterBefore(new AuthenticatorFilter(impl), UsernamePasswordAuthenticationFilter.class) .authorizeHttpRequests(c -> c.requestMatchers("/public/**").permitAll().anyRequest().authenticated()).sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) .csrf(c-> c.disable())  .userDetailsService(service);
    
    return http.build();
}
@Bean
public AuthenticationManager manager(HttpSecurity http) throws Throwable{
AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);

return builder.authenticationProvider(daoAuthenticationProvider()).getOrBuild();


}
@Bean

public PasswordEncoder encoder(){
    return new BCryptPasswordEncoder();
}
@Bean
DaoAuthenticationProvider daoAuthenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(encoder());
    provider.setUserDetailsService(service);

    return provider;
}

}
