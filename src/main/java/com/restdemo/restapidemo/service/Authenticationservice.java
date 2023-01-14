package com.restdemo.restapidemo.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.restdemo.restapidemo.error.DuplicateUserException;
import com.restdemo.restapidemo.error.EmptyFileException;
import com.restdemo.restapidemo.error.UserNotFoundException;
import com.restdemo.restapidemo.model.User;

public interface Authenticationservice {

    public User signup(User user) throws DuplicateUserException, EmptyFileException, NoSuchAlgorithmException;

    public ResponseEntity<Object> login(User user) throws UserNotFoundException;

    public ResponseEntity<Object> logout(User user) throws UserNotFoundException;

    public List<User> getAllUsers() throws UserNotFoundException;

    public User getSingleUser(Long id) throws UserNotFoundException;

    // public void deleteUser(Long id) throws UserNotFoundException;
    // public User deleteUser(Long userId, User user) throws UserNotFoundException;

    public User updateUser(Long id, User user) throws UserNotFoundException;

    public String isActive();

    public String setStatusToFales();

    public User deactivateUser(Long id, User user) throws UserNotFoundException;

    public User desableUser(Long id, User user) throws UserNotFoundException;

}
