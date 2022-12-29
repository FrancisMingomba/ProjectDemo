package com.restdemo.restapidemo.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public interface DepartmentNotFoundException {
    public ResponseEntity<ErrorMessage> departmentNotFoundException(DepartmentNotFoundException exception,
            WebRequest request);

    public String getMessage();
}
