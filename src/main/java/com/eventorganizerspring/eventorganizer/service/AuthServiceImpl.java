package com.eventorganizerspring.eventorganizer.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eventorganizerspring.eventorganizer.dtos.PersonDto;
import com.eventorganizerspring.eventorganizer.interfaces.AuthService;
import com.eventorganizerspring.eventorganizer.models.Person;
import com.eventorganizerspring.eventorganizer.repositories.PersonRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Service
@Log4j2
public class AuthServiceImpl implements AuthService {

    private final JwtServiceImpl impl;
    private final PersonRepository repository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager manager;
    @Override
    public ResponseCookie authenticate(PersonDto entity) {
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withStringMatcher(StringMatcher.EXACT)
                .withIncludeNullValues();

        Example<Person> example = Example.of(new Person(entity), matcher);
        Person person = repository.findOne(example)
                .orElseThrow(EntityNotFoundException::new);
   Authentication authentication = manager.authenticate(
            new UsernamePasswordAuthenticationToken(entity.email(), entity.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

      
        String token = impl.generateToken(repository.findById(person.getId()).get());
        ResponseCookie cook = ResponseCookie.from("Bearer", token).httpOnly(true).path("/").build();

        return cook;
    }

    public final Integer findIdByEmail(Object object) {
        if (object instanceof Person) {
            Person p = (Person) object;
            return repository.findIdByEmail(p.getEmail());
        } else if (object instanceof PersonDto) {
            PersonDto dto = (PersonDto) object;
            return repository.findIdByEmail(dto.email());
        }
        throw new EntityNotFoundException();
    }

    @Override
    public void cadastro(PersonDto dto) {
        if (repository.existsByEmail(dto.email())) {
            throw new EntityExistsException();
        }
        var e = new Person(dto);
        log.info("A senha do user é: " + e.getPassword());
        String encoded = encoder.encode(e.getPassword());
        log.info("A senha criptografada é: " + encoded);
        e.setPassword(encoded);
        repository.saveAndFlush(e);
    }

    @Override
    public UsernamePasswordAuthenticationToken authenticationToken(String token) {
        UsernamePasswordAuthenticationToken tokenAuthentication = new UsernamePasswordAuthenticationToken(
                impl.getClaims(token).get("email"), impl.getClaims(token).getSubject());
        return tokenAuthentication;
    }
}
