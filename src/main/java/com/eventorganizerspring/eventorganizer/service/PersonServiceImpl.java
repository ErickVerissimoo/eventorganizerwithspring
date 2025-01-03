package com.eventorganizerspring.eventorganizer.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eventorganizerspring.eventorganizer.interfaces.PersonService;
import com.eventorganizerspring.eventorganizer.models.Person;
import com.eventorganizerspring.eventorganizer.repositories.PersonRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {
        private final PersonRepository repository;

    @Transactional
    @Override
    public Person addPerson(Person p) {
        repository.saveAndFlush(p);

        return p;

    }
    @Transactional
    @Override
    public Person deletePerson(Integer id) {
        val e = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        repository.deleteById(id);
        return e;
    }

    @Override
    public Person updatePerson(Person p) {
repository.saveAndFlush(p);
return p;
    }

    @Override
    public Person getPerson(Integer id) {
    return repository.findById(id).orElseThrow(EntityNotFoundException::new);    
    
    }

    @Override
    public Person getPerson(Person person) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.EXACT);
        Example<Person> example = Example.of(person, matcher);
    
        return    repository.findOne(example).orElseThrow(EntityNotFoundException::new);

    }

    @Override
    public Person deletePerson(Person p) {
        
    repository.delete(p);

return p ;
    }
@Override
public List<Person> findAll() { 
return repository.findAll();
}
@Override
public List<Person> findAllByExample(Person person) 

{
    ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withIncludeNullValues();
Example<Person> example = Example.of(person, matcher);
Sort sort = Sort.by("name").descending();
return repository.findAll(example, sort);
}
}
