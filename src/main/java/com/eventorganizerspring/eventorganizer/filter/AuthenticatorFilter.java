package com.eventorganizerspring.eventorganizer.filter;

import java.io.IOException;
import java.util.Objects;

import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import com.eventorganizerspring.eventorganizer.interfaces.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticatorFilter extends OncePerRequestFilter {
    private final JwtService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
               String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (Objects.nonNull(authorization) && authorization.startsWith("Bearer ")) {
            try {
                String token = authorization.substring(7);
                service.validateToken(token);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or missing token");
                return;
            }
        }

        filterChain.doFilter(request, response);

      }}