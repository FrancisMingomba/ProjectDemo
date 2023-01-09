package com.restdemo.restapidemo.api;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restdemo.restapidemo.api.ControllerImplService.UserApiImplService;
import com.restdemo.restapidemo.error.DuplicateUserException;
import com.restdemo.restapidemo.error.EmptyFileException;
//import com.cloudseal.rest.exception.DuplicateUserException;
//import com.cloudseal.rest.exception.DuplicateUserException;
import com.restdemo.restapidemo.model.User;
import com.restdemo.restapidemo.service.Authenticationservice;
import com.restdemo.restapidemo.error.UserNotFoundException;

@RestController
@RequestMapping("")
public class UserApi {

    @Autowired
    UserApiImplService userApiImplService;

    @Autowired
    Authenticationservice authenticationservice;

    @PostMapping("/users")
    public User signup(@RequestBody User userFromClients)
            throws DuplicateUserException, EmptyFileException, NoSuchAlgorithmException {

        return authenticationservice.signup(userFromClients);

    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<Object> getAllUsers()
            throws UserNotFoundException {
        return ResponseEntity.ok(authenticationservice.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getSingleUser(@PathVariable Long id)
            throws UserNotFoundException {
        return ResponseEntity.ok(authenticationservice.getSingleUser(id));
    }



    // @PutMapping("/users/{id}")
    // public User updateUser(@PathVariable Long id, @RequestBody User user)
    // throws UserNotFoundException {

    // return authenticationservice.updateUser(id, user);

    // }

    // -------------------------------------------------------
    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user)
            throws UserNotFoundException {

        return authenticationservice.updateUser(id, user);


    }
    // ------------------------------------------------------

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) throws UserNotFoundException {

        authenticationservice.deleteUser(id);
        return null;
    }


}
