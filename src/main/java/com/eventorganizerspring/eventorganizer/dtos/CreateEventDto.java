package com.eventorganizerspring.eventorganizer.dtos;

import java.util.List;

import lombok.Data;
@Data

public class CreateEventDto{
    private AddressDto addressDto; private String title, description;private Integer categoria;private List<LoginAndSignUpDto> dtos; 


}
