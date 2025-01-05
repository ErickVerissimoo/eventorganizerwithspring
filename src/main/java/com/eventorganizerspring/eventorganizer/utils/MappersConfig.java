package com.eventorganizerspring.eventorganizer.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eventorganizerspring.eventorganizer.dtos.CreateEventDto;
import com.eventorganizerspring.eventorganizer.models.Event;

@Configuration
public class MappersConfig {
@Bean(value = "eventMapper")
public ModelMapper createEventMapper(){
ModelMapper mapper = new ModelMapper();

TypeMap<CreateEventDto, Event> typeMapper = mapper.createTypeMap(CreateEventDto.class, Event.class);
typeMapper.addMappings(m -> m.map(CreateEventDto::getCategoria, Event::setCategoria));
typeMapper.addMappings(m -> m.map(CreateEventDto::getDtos, Event::setPersons));
typeMapper.addMappings(m -> m.map(CreateEventDto::getAddressDto, Event::setAddress));
return mapper;
}
}
