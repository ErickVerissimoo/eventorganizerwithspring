package com.eventorganizerspring.eventorganizer.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.eventorganizerspring.eventorganizer.dtos.PersonDto;
import com.eventorganizerspring.eventorganizer.interfaces.AuthService;
import com.eventorganizerspring.eventorganizer.models.Person;
import com.eventorganizerspring.eventorganizer.repositories.PersonRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
private final JwtServiceImpl impl;
private final PersonRepository repository;

    @Override
    public String authenticate(PersonDto dto) {
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.EXACT).withIncludeNullValues();

        Example<Person> example = Example.of(new Person(dto), matcher);
        if(repository.findOne(example).isPresent()){
            String token = impl.generateToken(repository.findById(findIdByEmail(example.getProbe())).get());
            return token;
        }      
        throw new EntityNotFoundException();  
    }
public final Integer findIdByEmail(Object object){
    if(object instanceof Person){
        Person p= (Person) object;
        return repository.findIdByEmail(p.getEmail());
    }
    else if(object instanceof PersonDto){
        PersonDto dto = (PersonDto) object;
        return repository.findIdByEmail(dto.email());
    }
   throw new EntityNotFoundException();
}
   
    @Override
    public void cadastro(PersonDto dto) {
        if(repository.existsByEmail(dto.email())){
            throw new EntityNotFoundException();
        }
        repository.saveAndFlush(new Person(dto));
    }

    @Override
    public UsernamePasswordAuthenticationToken authenticationToken(String token) {
        UsernamePasswordAuthenticationToken tokenAuthentication = new UsernamePasswordAuthenticationToken(impl.getClaims(token).get("email"), impl.getClaims(token).getSubject());
        return tokenAuthentication;
    
    }

}
