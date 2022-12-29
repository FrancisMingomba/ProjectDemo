package com.restdemo.restapidemo.error;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus

public class RestResponseEntityExceptionExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(UserNotFoundException exception,
            WebRequest request) {
        return badRequest(exception.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> authenticationException(AuthenticationException exception,
            WebRequest request) {
        return badRequest(exception.getMessage());
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<String> authenticationException(DuplicateUserException exception,
            WebRequest request) {
        return badRequest(exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentException(DuplicateUserException exception,
            WebRequest request) {
        return badRequest(exception.getMessage());
    }

    // @ExceptionHandler(UnknownApplicationTypeExcption.class)
    // public ResponseEntity<String> unknownVisaTypeException(DuplicateUserException
    // exception,
    // WebRequest request) {
    // return badRequest(exception.getMessage());
    // }

    private ResponseEntity<String> badRequest(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(message);
    }

}
