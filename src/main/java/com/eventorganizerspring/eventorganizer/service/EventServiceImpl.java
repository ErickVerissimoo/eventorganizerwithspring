package com.eventorganizerspring.eventorganizer.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eventorganizerspring.eventorganizer.interfaces.EventService;
import com.eventorganizerspring.eventorganizer.models.Event;
import com.eventorganizerspring.eventorganizer.repositories.EventRepository;
import com.eventorganizerspring.eventorganizer.utils.EventSpecifications;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository repository;
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

}
