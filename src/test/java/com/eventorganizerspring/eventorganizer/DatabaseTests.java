package com.eventorganizerspring.eventorganizer;


import static org.instancio.Select.field;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import com.eventorganizerspring.eventorganizer.models.Event;
import com.eventorganizerspring.eventorganizer.repositories.EventRepository;
import com.eventorganizerspring.eventorganizer.utils.EventSpecifications;

import jakarta.inject.Inject;
@DataJpaTest(showSql = true)
@ActiveProfiles("test")
public class DatabaseTests {
private List<Event> evento;
@Inject private EventRepository repository;
 @BeforeEach

 void teste(){
    evento = new ArrayList<>();
    for(int i =0; i<10; i++){
        System.out.println("Teste de setup");
              LocalDate nextDays = LocalDate.now().plusDays(2);
            LocalDateTime startOfDay = LocalDateTime.now();
            LocalDateTime endOfDay = nextDays.atTime(23, 59, 59);
Event event = Instancio.of(Event.class).generate(field("dataMarcada"), gen -> gen.temporal().localDateTime().range(startOfDay, endOfDay)).ignore( field("persons")).create();
evento.add(event);   
}
repository.saveAllAndFlush(evento);
    System.out.println(evento);
    System.out.println("----------------------------");
}
@Test
void second(){
    System.out.println("teste pra valer;");
    var e = repository.findAll(EventSpecifications.findNextDayEvents());
    e.forEach(System.out::println);
    Assert.notNull(e, "não pode ser nulo");
}
}
