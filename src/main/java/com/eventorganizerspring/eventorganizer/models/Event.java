package com.eventorganizerspring.eventorganizer.models;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
@Data
@Entity
public class Event {
@Id private Integer id;
@CreationTimestamp
private LocalDateTime timeCreation;
@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
private LocalDateTime dataMarcada;
private String title;
private String description;
@ManyToMany(mappedBy = "events")
private List<Person> persons;
}
