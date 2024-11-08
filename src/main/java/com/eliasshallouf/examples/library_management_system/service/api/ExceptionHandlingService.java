package com.eliasshallouf.examples.library_management_system.service.api;

import com.eliasshallouf.examples.library_management_system.domain.model.exceptions.ExceptionResponse;
import com.eliasshallouf.examples.library_management_system.domain.model.exceptions.RecordNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlingService extends ResponseEntityExceptionHandler {
    @ExceptionHandler({
        RuntimeException.class
    })
    protected ResponseEntity<Object> handleGeneralError(Exception ex, WebRequest request) {
        return ResponseEntity
            .status(HttpStatus.METHOD_NOT_ALLOWED)
            .body(new ExceptionResponse(ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return ResponseEntity
            .badRequest()
            .body(new ExceptionResponse(ex
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(",\n"))
            ));
    }

    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return ResponseEntity
            .badRequest()
            .body(new ExceptionResponse(ex
                    .getAllValidationResults()
                    .stream()
                    .map(error -> error
                            .getResolvableErrors()
                            .stream()
                            .map(MessageSourceResolvable::getDefaultMessage)
                            .collect(Collectors.joining(",\n"))
                    )
                    .collect(Collectors.joining(",\n"))
            ));
    }

    @ExceptionHandler({
        RecordNotFoundException.class
    })
    protected ResponseEntity<Object> handleMissingData(Exception ex, WebRequest request) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new ExceptionResponse(ex.getMessage()));
    }
}