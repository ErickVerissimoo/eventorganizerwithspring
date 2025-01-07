package com.eventorganizerspring.eventorganizer.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eventorganizerspring.eventorganizer.dtos.CreateEventDto;
import com.eventorganizerspring.eventorganizer.interfaces.Mapper;
import com.eventorganizerspring.eventorganizer.models.Event;
@Component
public class MapperEvent implements Mapper<CreateEventDto, Event> {
    
    private final ModelMapper mapper;
    @Autowired

    public MapperEvent( ModelMapper mapper ) {
        this.mapper=mapper;
    }
    @Override
    public Event toEntity(CreateEventDto dto) {
        return mapper.map(dto, Event.class);
    }


}
