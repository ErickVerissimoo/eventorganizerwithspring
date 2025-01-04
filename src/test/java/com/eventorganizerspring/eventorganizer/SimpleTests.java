package com.eventorganizerspring.eventorganizer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.eventorganizerspring.eventorganizer.service.JwtServiceImpl;

public class SimpleTests {
    @InjectMocks
private JwtServiceImpl impl;
@Test
public void name() {
  System.out.println(impl.generateToken("oi", "ola"));
}
@BeforeEach
public void setup(){
MockitoAnnotations.openMocks(this);
}
}
