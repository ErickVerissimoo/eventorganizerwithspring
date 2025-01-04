package com.eventorganizerspring.eventorganizer.controller;

import java.util.Map;
import java.util.concurrent.Callable;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.eventorganizerspring.eventorganizer.dtos.PersonDto;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private final ThreadPoolTaskExecutor exec;
@PostMapping("/signup")
public DeferredResult<String> method (){
    DeferredResult<String> mymethod = new DeferredResult<>();
    exec.submit(() -> mymethod.setResult("ola mundo"));    
    return mymethod;
}
@PostMapping("/signin")
public Callable<ResponseEntity<Map<String, String>>> login(@RequestBody PersonDto entity) {
    
    return () ->{
      return  ResponseEntity.ok().body(Map.of("message", "usuário logado"));
};



}
}