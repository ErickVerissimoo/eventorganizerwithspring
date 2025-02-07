package com.eventorganizerspring.eventorganizer.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eventorganizerspring.eventorganizer.interfaces.EventService;
import com.eventorganizerspring.eventorganizer.interfaces.JwtService;
import com.eventorganizerspring.eventorganizer.models.Event;
import com.eventorganizerspring.eventorganizer.repositories.EventRepository;
import com.eventorganizerspring.eventorganizer.repositories.PersonRepository;
import com.eventorganizerspring.eventorganizer.utils.EventSpecifications;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository repository;
    private final ModelMapper mapper;
    private final JwtService service;
    private final PersonRepository repository2;
    @Override
    public List<Event> findAll() {
return repository.findAll();
    }

    @Override
    public List<Event> findAllByExample(Event event) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIncludeNullValues();   
        Example<Event> example = Example.of(event, matcher);
        return repository.findAll(example);
    }

    @Override
    public List<Event> findAllNextDayEvents() {
        
   return repository.findAll(EventSpecifications.findNextDayEvents(), Sort.by("dataMarcada").ascending());
    
    }

    @Override
    public void createEvent(Event event, String token) {
int e = service.extractUserId(token);
Event evento = new Event();
var user = repository2.findById( e).orElseThrow(EntityNotFoundException::new);

evento.getPersons().addLast(user);;
user.getEvents().addFirst(evento);
repository.saveAndFlush(event);
        repository2.saveAndFlush(user);
    }

    @Override
    public Event findEvent(Integer id) {
return repository.findById(Long.valueOf(id.toString())).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Event findEvent(Event event) {
ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.EXACT).withIncludeNullValues();
Example<Event> example = Example.of(event, matcher);
return repository.findOne(example).orElseThrow(EntityNotFoundException::new);

    }

}
