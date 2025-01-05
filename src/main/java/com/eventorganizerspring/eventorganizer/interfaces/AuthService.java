package com.eventorganizerspring.eventorganizer.interfaces;

import org.springframework.http.ResponseCookie;

import com.eventorganizerspring.eventorganizer.dtos.LoginAndSignUpDto;

public interface AuthService {
ResponseCookie authenticate(LoginAndSignUpDto dto);
void cadastro(LoginAndSignUpDto dto);
}
