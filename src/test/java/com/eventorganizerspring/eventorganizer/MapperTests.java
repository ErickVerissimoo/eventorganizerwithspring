package com.eventorganizerspring.eventorganizer;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import com.eventorganizerspring.eventorganizer.dtos.CreateEventDto;
import com.eventorganizerspring.eventorganizer.models.Event;

public class MapperTests {

    

    @Test
    void tests() {
    ModelMapper mapper = new ModelMapper();

TypeMap<CreateEventDto, Event> typeMapper = mapper.createTypeMap(CreateEventDto.class, Event.class);
typeMapper.addMappings(m -> m.map(CreateEventDto::getCategoria, Event::setCategoria));
typeMapper.addMappings(m -> m.map(CreateEventDto::getDtos, Event::setPersons));
typeMapper.addMappings(m -> m.map(CreateEventDto::getAddressDto, Event::setAddress));
      
CreateEventDto dto = Instancio.of(CreateEventDto.class).create();
        System.out.println(dto);
        System.out.println(dto.getDtos().size());

     System.out.println(mapper==null);

        var e = mapper.map(dto, Event.class);
        System.out.println(e.getCategoria().ordinal());
    }
    
}
