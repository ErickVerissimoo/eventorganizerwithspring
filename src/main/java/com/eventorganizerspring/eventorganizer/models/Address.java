package com.eventorganizerspring.eventorganizer.models;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Data
@Entity
public class Address {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String rua;
private String cidade;
private Integer numero;
@OneToMany
@JoinColumn(name = "event_id")
private Set<Event> events;
}
