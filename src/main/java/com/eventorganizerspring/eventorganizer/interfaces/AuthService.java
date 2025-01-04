package com.eventorganizerspring.eventorganizer.interfaces;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.eventorganizerspring.eventorganizer.dtos.PersonDto;

public interface AuthService {
String authenticate(PersonDto dto);
void cadastro(PersonDto dto);
UsernamePasswordAuthenticationToken authenticationToken (String token);
}
