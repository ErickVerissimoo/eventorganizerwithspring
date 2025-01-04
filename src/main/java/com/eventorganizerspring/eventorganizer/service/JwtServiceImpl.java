package com.eventorganizerspring.eventorganizer.service;

import java.security.Key;
import java.sql.Date;
import java.time.Duration;

import org.springframework.stereotype.Service;

import com.eventorganizerspring.eventorganizer.interfaces.JwtService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtServiceImpl implements JwtService {
private static final Key key = Keys.hmacShaKeyFor("minha chave mega secreta para caramba".getBytes());
public String generateToken(String emissor,String userId ){
    return Jwts.builder().setId(emissor).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + Duration.ofMinutes(20).toMinutes())).setSubject(userId).signWith(key).compact();
}


@Override
public boolean validateToken(String token) {
    
return true;
}


@Override
public Integer extractUserId(String token) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'extractUserId'");
}
}
