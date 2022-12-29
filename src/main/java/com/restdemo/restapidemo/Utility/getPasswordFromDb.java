package com.restdemo.restapidemo.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.restdemo.restapidemo.error.EmptyFileException;
import com.restdemo.restapidemo.model.User;
import com.restdemo.restapidemo.repository.UserRepository;

@Service
public class getPasswordFromDb {

    private UserRepository userRepository;

    // List<User> userPassword = new ArrayList<User>();

    public List<User> readPasswordFromDb() throws EmptyFileException {

        List<User> userPassword = new ArrayList<User>();

        List<User> userInDb = StreamSupport.stream(this.userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        for (User user : userInDb) {
            if (user.getPassword().isEmpty()) {
                throw new EmptyFileException();
            }

            userPassword.add(user);
        }

        return userPassword;
    }

    public String readSaltFromDb() throws EmptyFileException {

        ArrayList<User> userSalt = new ArrayList<User>();

        List<User> userInDb = StreamSupport.stream(this.userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        for (User user : userInDb) {
            if (user.getSalt().isEmpty()) {
                throw new EmptyFileException();
            }

            userSalt.add(user);
        }

        return userSalt.toString();
    }

    public String newPasswordFromSaltAndUserPassword() throws EmptyFileException {

        String salt = readSaltFromDb();

        String password = readSaltFromDb();

        String newPassword = salt + password;

        return newPassword;
    }

}
