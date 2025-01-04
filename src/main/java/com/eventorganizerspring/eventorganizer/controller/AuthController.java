package com.eventorganizerspring.eventorganizer.controller;

import java.util.Map;
import java.util.concurrent.Callable;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.eventorganizerspring.eventorganizer.dtos.PersonDto;
import com.eventorganizerspring.eventorganizer.interfaces.AuthService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private final ThreadPoolTaskExecutor exec;
    private final AuthService service;
    private final AuthenticationManager manager;
@PostMapping("/signup")
public DeferredResult<Map<String, String>> method (@RequestBody PersonDto dtp){
    DeferredResult<Map<String,String>> mymethod = new DeferredResult<>();
 
    exec.submit(() ->{ mymethod.setResult(Map.of("message", "user cadastrado"));
       
    service.cadastro(dtp);
    
});    
    
    return mymethod;
}
@PostMapping("/signin")
public Callable<ResponseEntity<String>> login(@RequestBody PersonDto entity) {
  
    return () ->{
        var token = service.authenticate(entity);
        manager.authenticate(service.authenticationToken(token));
       HttpHeaders header = new HttpHeaders();
       header.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
      return  ResponseEntity.ok().headers(header).build();
};



}
}