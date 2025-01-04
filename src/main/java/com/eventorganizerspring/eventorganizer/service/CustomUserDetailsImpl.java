package com.eventorganizerspring.eventorganizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.eventorganizerspring.eventorganizer.repositories.PersonRepository;

import lombok.Setter;
@Component
public class CustomUserDetailsImpl implements UserDetailsService{
    @Setter @Autowired @Lazy
    private PasswordEncoder encoder;
    private final PersonRepository repository;
    public CustomUserDetailsImpl(PasswordEncoder encoder, PersonRepository repository) {
        this.encoder=encoder;
        this.repository=repository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       return User.builder().username(email).password(repository.findPasswordByEmail(email)) .passwordEncoder(s -> encoder.toString()).build();

    }

}
