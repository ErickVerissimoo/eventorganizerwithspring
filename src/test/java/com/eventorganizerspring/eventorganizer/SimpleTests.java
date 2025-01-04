package com.eventorganizerspring.eventorganizer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.eventorganizerspring.eventorganizer.models.Person;
import com.eventorganizerspring.eventorganizer.service.JwtServiceImpl;

public class SimpleTests {
    @InjectMocks
private JwtServiceImpl impl;
@Test
public void name() {
  Person pessoa = new Person();
  pessoa.setEmail("email");
  pessoa.setId(1);
  pessoa.setName("erick");
 String token = impl.generateToken(pessoa);
 System.out.println(token);
 System.out.println("--------------------");
 String[] partes = impl.getParts(token);
 System.out.println(partes[1]);
}
@BeforeEach
public void setup(){
MockitoAnnotations.openMocks(this);
}
}
