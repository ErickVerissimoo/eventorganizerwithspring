package com.eventorganizerspring.eventorganizer.dtos;

import java.util.List;

import com.eventorganizerspring.eventorganizer.models.Event;

import lombok.Data;

@Data
public class AddressDto {

private String rua, cidade;
private Integer numero;

private List<Event> events;
}

