package com.restdemo.restapidemo.api;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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

@RestController
@RequestMapping("")
public class UserApi {

    @Autowired
    UserApiImplService userApiImplService;

    @Autowired
    Authenticationservice authenticationservice;

    @PostMapping("/users")
    public User signup(@RequestBody User userFromClients)
            throws DuplicateUserException, EmptyFileException, NoSuchAlgorithmException,
            com.restdemo.restapidemo.error.NoSuchAlgorithmException {

        return authenticationservice.signup(userFromClients);

    }

}
