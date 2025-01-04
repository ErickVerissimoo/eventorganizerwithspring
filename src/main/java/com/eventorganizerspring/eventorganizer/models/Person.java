package com.eventorganizerspring.eventorganizer.models;

import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.eventorganizerspring.eventorganizer.dtos.PersonDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PostPersist;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
@Entity
public class Person {
    @Id
private Integer id;
private String name;
private String password;
private String email;
@ManyToMany
@JoinTable(
name = "event_person",
joinColumns = { @JoinColumn(name = "person_key")},inverseJoinColumns = @JoinColumn(name = "event_id")
)
private Set<Event> events;
@PostPersist
public void post(){
    System.out.println("O usuário de nome:" + name + " foi persistido com sucesso");
}
public Person(PersonDto dto){
    BeanUtils.copyProperties(dto, this);
                        
}

}
