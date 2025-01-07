package com.eventorganizerspring.eventorganizer.interfaces;

import java.util.List;

import com.eventorganizerspring.eventorganizer.models.Event;

public interface EventService{
List<Event> findAll();
List<Event> findAllByExample(Event event);
List<Event> findAllNextDayEvents();
void createEvent(Event event, String token);
Event findEvent(Integer id);
Event findEvent(Event event);
}
