package com.eventorganizerspring.eventorganizer.filter;

import java.io.IOException;
import java.util.Objects;

import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.eventorganizerspring.eventorganizer.interfaces.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
@AllArgsConstructor

public class AuthenticatorFilter extends OncePerRequestFilter {
    private JwtService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
              ServletWebRequest webreq = new ServletWebRequest(request, response);
                 if(Objects.nonNull(webreq.getHeader(HttpHeaders.AUTHORIZATION))) {
                    String authorization= webreq.getHeader(HttpHeaders.AUTHORIZATION);
                    String[] header = authorization.split(" ");
              String token = header[1];
              service.validateToken(token);
                 }
                filterChain.doFilter(request, response);
    }

   
}
