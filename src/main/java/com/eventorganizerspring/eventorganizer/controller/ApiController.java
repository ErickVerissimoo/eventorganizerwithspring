package com.eventorganizerspring.eventorganizer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventorganizerspring.eventorganizer.dtos.CreateEventDto;
import com.eventorganizerspring.eventorganizer.interfaces.EventService;
import com.eventorganizerspring.eventorganizer.interfaces.PersonService;
import com.eventorganizerspring.eventorganizer.utils.MapperEvent;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
public class ApiController {
private final EventService service;
private final MapperEvent mapper;
private final PersonService service2;
@GetMapping("/{id}")
public String getEvent(@PathVariable String id) {
    return new String();
}
@PostMapping
public ResponseEntity<String> postEvent(@RequestBody  CreateEventDto dto, @CookieValue("Bearer") String token) {
    service.createEvent(mapper.toEntity(dto));

    return ResponseEntity.ok("tudo certo");
}

}
