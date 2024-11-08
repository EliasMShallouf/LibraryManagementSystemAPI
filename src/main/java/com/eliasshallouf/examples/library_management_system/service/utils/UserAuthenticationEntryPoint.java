package com.eliasshallouf.examples.library_management_system.service.utils;

import com.eliasshallouf.examples.library_management_system.domain.model.exceptions.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        try {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            new ObjectMapper()
                .writeValue(
                    response.getOutputStream(),
                    new ExceptionResponse(exception.getMessage())
                );
        } catch (Exception e) {
            response
                .getOutputStream()
                .println("Error in sending authentication exception info");
        }
    }
}