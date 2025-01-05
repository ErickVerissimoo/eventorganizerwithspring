package com.eventorganizerspring.eventorganizer.utils;

import org.springframework.stereotype.Component;

import com.eventorganizerspring.eventorganizer.dtos.LoginAndSignUpDto;
import com.eventorganizerspring.eventorganizer.interfaces.Mapper;
import com.eventorganizerspring.eventorganizer.models.Person;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MapperPerson implements Mapper<LoginAndSignUpDto, Person> {@Override
    public Person toEntity(LoginAndSignUpDto dto) {

return null;
    }

}
