package com.eventorganizerspring.eventorganizer.service;

import org.springframework.security.authentication.AuthenticationManager;

import com.eventorganizerspring.eventorganizer.dtos.PersonDto;
import com.eventorganizerspring.eventorganizer.interfaces.AuthService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
private final AuthenticationManager manager;



    @Override
    public boolean authenticate(PersonDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
    }

    @Override
    public boolean cadastro(PersonDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cadastro'");
    }

}
