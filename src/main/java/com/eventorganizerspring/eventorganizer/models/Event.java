package com.eventorganizerspring.eventorganizer.models;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.eventorganizerspring.eventorganizer.utils.Categoria;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Data
@Entity
public class Event {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
@Id private Integer id;
@CreationTimestamp
private LocalDateTime timeCreation;
private LocalDateTime dataMarcada;
private String title;
private String description;
@ManyToMany(mappedBy = "events")
private List<Person> persons;
@Enumerated(EnumType.ORDINAL)
private Categoria categoria;
@ManyToOne
private Address address;
}
