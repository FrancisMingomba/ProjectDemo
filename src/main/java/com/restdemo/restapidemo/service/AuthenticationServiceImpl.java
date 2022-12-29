package com.restdemo.restapidemo.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restdemo.restapidemo.Utility.SecurePassword;
import com.restdemo.restapidemo.error.DuplicateUserException;
import com.restdemo.restapidemo.error.EmptyFileException;
import com.restdemo.restapidemo.error.UserNotFoundException;
import com.restdemo.restapidemo.model.User;
import com.restdemo.restapidemo.repository.UserRepository;

@Service
public class AuthenticationServiceImpl implements Authenticationservice {

    public String saltGenerator() {
        final int SALT_LENGTH = 8;

        // return RandomStringUtils.randomAlphabetic(SALT_LENGTH);
        return RandomStringUtils.randomAlphanumeric(SALT_LENGTH);

    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    SecurePassword securePassword;

    public User signup(User userFromClient) throws DuplicateUserException, EmptyFileException, NoSuchAlgorithmException,
            com.restdemo.restapidemo.error.NoSuchAlgorithmException {
        if (alreadyExist(userFromClient))
            throw new DuplicateUserException("User already exist");

        byte[] _salt_value = getSalt();

        String saltValue = _salt_value.toString();

        userFromClient.setSalt(saltValue);

        String passwordFromUser = userFromClient.getPassword();

        String newPassword = saltValue + passwordFromUser;

        String _newPassword = securePassword.getSecurePassword(newPassword, _salt_value);

        userFromClient.setPassword(_newPassword);

        return this.userRepository.save(userFromClient);
    }

    private boolean alreadyExist(User userFromClient) {

        return this.userRepository.findUserByEmail(userFromClient.getEmail()) != null;

    }

    public String salt() {
        final int LENGTH_OF_SALT = 8;
        return RandomStringUtils.randomAlphanumeric(LENGTH_OF_SALT);
    }

    public byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    @Override
    public ResponseEntity<Object> login(User user) throws UserNotFoundException {

        return null;
    }

    @Override
    public ResponseEntity<Object> logout(User user) throws UserNotFoundException {

        return null;
    }

}
