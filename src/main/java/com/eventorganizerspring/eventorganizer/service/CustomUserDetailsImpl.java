package com.eventorganizerspring.eventorganizer.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.eventorganizerspring.eventorganizer.repositories.PersonRepository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
@AllArgsConstructor
@Log4j2
public class CustomUserDetailsImpl implements UserDetailsService{

    private final PersonRepository repository;
   
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var person = repository.findByEmail(email).orElseThrow();
        
        log.info("A senha do user é: " + person.getPassword());

        return User.builder().username(person.getEmail()).password(person.getPassword()) .build();

    }

}
