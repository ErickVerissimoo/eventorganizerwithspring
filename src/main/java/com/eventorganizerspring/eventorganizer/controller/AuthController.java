package com.eventorganizerspring.eventorganizer.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventorganizerspring.eventorganizer.dtos.PersonDto;
import com.eventorganizerspring.eventorganizer.interfaces.AuthService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/public/auth")
public class AuthController {
    private final ThreadPoolTaskExecutor exec;
    private final AuthService service;
    private final AuthenticationManager manager;
@PostMapping("/signup")
public ResponseEntity<String> method (@RequestBody PersonDto dtp){
    service.cadastro(dtp);
    return ResponseEntity.ok().body("User cadastrado");
}
@PostMapping("/signin")
public ResponseEntity<String> login(@RequestBody PersonDto entity) {
  
        var token = service.authenticate(entity);
    Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(entity.email(), entity.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    return  ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
};



}
