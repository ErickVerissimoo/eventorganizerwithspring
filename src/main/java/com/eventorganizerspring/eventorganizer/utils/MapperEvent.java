package com.eventorganizerspring.eventorganizer.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.eventorganizerspring.eventorganizer.dtos.CreateEventDto;
import com.eventorganizerspring.eventorganizer.interfaces.Mapper;
import com.eventorganizerspring.eventorganizer.models.Event;

import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class MapperEvent implements Mapper<CreateEventDto, Event> {
    @Qualifier("eventMapper")
    private final ModelMapper mapper;
    @Override
    public Event toEntity(CreateEventDto dto) {
        return mapper.map(dto, Event.class);
    }


}
