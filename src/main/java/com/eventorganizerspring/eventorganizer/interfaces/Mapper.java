package com.eventorganizerspring.eventorganizer.interfaces;

public interface Mapper<D, T>{
T toEntity(D dto);
}
