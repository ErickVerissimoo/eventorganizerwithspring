package com.eventorganizerspring.eventorganizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventorganizerspring.eventorganizer.models.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
