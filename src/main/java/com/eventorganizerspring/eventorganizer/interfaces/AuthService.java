package com.eventorganizerspring.eventorganizer.interfaces;

import com.eventorganizerspring.eventorganizer.dtos.PersonDto;

public interface AuthService {
boolean authenticate(PersonDto dto);
boolean cadastro(PersonDto dto);
}
