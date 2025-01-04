package com.eventorganizerspring.eventorganizer.interfaces;

import com.eventorganizerspring.eventorganizer.models.Person;

import io.jsonwebtoken.Claims;

public interface JwtService {
String generateToken( Person person);
Integer extractUserId(String token);
Boolean validateToken(String token);
boolean isExpired(String token);
Claims getClaims(String Token);
String[] getParts(String token);
}
