package com.restdemo.restapidemo.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public interface ResponseEntityExceptionHandler {
    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<String> authenticationException(DuplicateUserException exception, WebRequest request);

}
