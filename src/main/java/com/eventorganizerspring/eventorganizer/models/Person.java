package com.eventorganizerspring.eventorganizer.models;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.eventorganizerspring.eventorganizer.dtos.LoginAndSignUpDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String name;
private String password;
private String email;
@ManyToMany
@JoinTable(
name = "event_person",
joinColumns = { @JoinColumn(name = "person_key")},inverseJoinColumns = @JoinColumn(name = "event_id"))
private List<Event> events;

public Person(LoginAndSignUpDto dto){
    BeanUtils.copyProperties(dto, this);
                        
}
}
