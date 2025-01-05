package com.eventorganizerspring.eventorganizer.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventorganizerspring.eventorganizer.dtos.LoginAndSignUpDto;
import com.eventorganizerspring.eventorganizer.interfaces.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/public/auth")
public class AuthController {

    private final ThreadPoolTaskExecutor exec;
    private final AuthService service;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody LoginAndSignUpDto dto) {
        service.cadastro(dto);
        return ResponseEntity.ok().body("User cadastrado");
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody LoginAndSignUpDto entity) {
     
        ResponseCookie cook =   service.authenticate(entity);
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, cook.toString())
            .body("Login realizado com sucesso");
    }

    @GetMapping
    public String getToken(@CookieValue(value = "Bearer") String token) {
        log.warn("O token do usuário é: " + token);
        return token;
    }
}
