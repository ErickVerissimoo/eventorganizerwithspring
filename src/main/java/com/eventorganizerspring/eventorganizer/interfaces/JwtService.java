package com.eventorganizerspring.eventorganizer.interfaces;

import java.sql.Date;
import java.time.Instant;

import io.jsonwebtoken.Jwts;

public interface JwtService {
String generateToken(String emissor, String userId);
boolean validateToken(String token);
Integer extractUserId(String token);
default boolean isExpired(String token, String password){

    return Jwts.parser().setSigningKey(password).parseClaimsJwt(token).getBody().getExpiration().after(Date.from(Instant.now()));
}
}
