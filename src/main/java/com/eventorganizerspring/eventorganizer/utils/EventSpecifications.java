package com.eventorganizerspring.eventorganizer.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.eventorganizerspring.eventorganizer.models.Event;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EventSpecifications {
public Specification<Event> findNextDayEvents(){
           LocalDate tomorrow = LocalDate.now().plusDays(1);
            LocalDateTime startOfDay = tomorrow.atStartOfDay();
            LocalDateTime endOfDay = tomorrow.atTime(23, 59, 59);

return (root, query, builder) ->
   builder.between(root.get("dataMarcada"), startOfDay, endOfDay);

}
}
