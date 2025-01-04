package com.eventorganizerspring.eventorganizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eventorganizerspring.eventorganizer.models.Person;


public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("Select u.password from Person u where u.email=:email")
    String findPasswordByEmail(@Param("email") String email);

}
