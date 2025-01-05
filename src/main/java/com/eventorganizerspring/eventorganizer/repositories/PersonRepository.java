package com.eventorganizerspring.eventorganizer.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eventorganizerspring.eventorganizer.models.Person;



public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("Select u.password from Person u where u.email=:email")
    String findPasswordByEmail(@Param("email") String email);
    @Query("Select u.id from Person u where u.email =:email")
    Integer findIdByEmail(@Param("email") String email);
    boolean existsByEmailAndId(String email, Integer id);
    boolean existsByEmail(String email);
    Optional<Person> findByEmail(String email);
}
