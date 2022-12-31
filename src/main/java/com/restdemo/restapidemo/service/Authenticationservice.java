package com.restdemo.restapidemo.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.http.ResponseEntity;

import com.restdemo.restapidemo.error.DuplicateUserException;
import com.restdemo.restapidemo.error.EmptyFileException;
import com.restdemo.restapidemo.error.UserNotFoundException;
import com.restdemo.restapidemo.model.User;

public interface Authenticationservice {

    public User signup(User user) throws DuplicateUserException, EmptyFileException, NoSuchAlgorithmException;

    public ResponseEntity<Object> login(User user) throws UserNotFoundException;

    public ResponseEntity<Object> logout(User user) throws UserNotFoundException;

    public ResponseEntity<Object> getAllUsers() throws UserNotFoundException;

    public User getSingleUser(Long id) throws UserNotFoundException;
}
