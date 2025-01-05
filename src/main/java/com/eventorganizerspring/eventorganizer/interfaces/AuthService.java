package com.eventorganizerspring.eventorganizer.interfaces;

import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.eventorganizerspring.eventorganizer.dtos.PersonDto;

public interface AuthService {
ResponseCookie authenticate(PersonDto dto);
void cadastro(PersonDto dto);
UsernamePasswordAuthenticationToken authenticationToken (String token);
}
