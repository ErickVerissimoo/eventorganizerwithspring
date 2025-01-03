package com.eventorganizerspring.eventorganizer.interfaces;

import java.util.List;

import com.eventorganizerspring.eventorganizer.models.Person;

public interface PersonService {
Person addPerson(Person p);
Person deletePerson(Integer id);
Person updatePerson(Person p);
Person getPerson(Integer id);
Person getPerson(Person person);
Person deletePerson(Person p);
List<Person> findAll();
List<Person> findAllByExample(Person person);
}
