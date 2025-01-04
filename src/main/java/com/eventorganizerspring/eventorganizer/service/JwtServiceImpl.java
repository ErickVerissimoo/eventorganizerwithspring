package com.eventorganizerspring.eventorganizer.service;

import java.security.Key;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.eventorganizerspring.eventorganizer.interfaces.JwtService;
import com.eventorganizerspring.eventorganizer.models.Person;
import com.eventorganizerspring.eventorganizer.repositories.PersonRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Service

public class JwtServiceImpl implements JwtService {
private static final Key key = Keys.hmacShaKeyFor("minha chave mega secreta para caramba".getBytes());
@Autowired
private  PersonRepository repository;    
public String generateToken(Person person) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", person.getEmail());
        claims.put("nome", person.getName());
        claims.put("id", person.getId());
        long expirationTime = System.currentTimeMillis() + Duration.ofMinutes(20).toMillis();

        return Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(expirationTime))
                .setSubject(person.getId().toString())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


@Override
public Boolean validateToken(String token) {
    Person person = repository.findById(extractUserId(token)).orElseThrow();
    
    Assert.isTrue(isExpired(token), "Token inválido");
    return Jwts.parserBuilder().setSigningKey(key).require("id", person.getId())!=null;
}

@Override
public Integer extractUserId(String token) {
return Integer.valueOf(Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject());
}

public boolean isExpired(String token){
    long expirationTime = System.currentTimeMillis() + Duration.ofMinutes(20).toMillis();

    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getExpiration().before(new Date(expirationTime));
    }
    public Claims getClaims(String Token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(Token).getBody();
    }
    @Override
    public String[] getParts(String token) {
        StringTokenizer tokenizer = new StringTokenizer(token, ".", false);
        List<String> partes = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            partes.add(tokenizer.nextToken());
        }
        return partes.stream().toArray(String[]::new);
        
    }

}
