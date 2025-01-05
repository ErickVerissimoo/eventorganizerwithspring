package com.eventorganizerspring.eventorganizer.filter;

import java.io.IOException;
import java.util.Objects;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.eventorganizerspring.eventorganizer.interfaces.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticatorFilter extends OncePerRequestFilter {
    private final JwtService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                
                Cookie cook = WebUtils.getCookie(request, "Bearer");
                if(Objects.nonNull(cook))
                {
                String token = cook.getValue();
                Boolean isTokenValid = service.validateToken(token);
                if(!isTokenValid){
                  response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário não autenticado");
return;
                }
                }
   
              
filterChain.doFilter(request, response);
      }
    
    }