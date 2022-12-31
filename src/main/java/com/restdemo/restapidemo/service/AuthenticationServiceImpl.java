package com.restdemo.restapidemo.service;

import java.util.List;
import java.util.Optional;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.restdemo.restapidemo.Utility.SecurePassword;
import com.restdemo.restapidemo.error.DuplicateUserException;
import com.restdemo.restapidemo.error.EmptyFileException;
import com.restdemo.restapidemo.error.UserNotFoundException;
import com.restdemo.restapidemo.model.User;
import com.restdemo.restapidemo.repository.UserRepository;

//import antlr.collections.List;

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

    public User signup(User userFromClient)

            throws DuplicateUserException, EmptyFileException, NoSuchAlgorithmException {
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

    // --------------------------------------------------------------------------------------

    // @GetMapping("")
    public ResponseEntity<Object> getAllUsers() throws UserNotFoundException {
        List<User> userInDb = StreamSupport.stream(this.userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        if (userInDb.isEmpty() || userInDb.size() == 0)
            throw new UserNotFoundException();
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public User getSingleUser(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent())
            throw new UserNotFoundException();

        return user.get();
    }

    // @PutMapping("/user/{id")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws UserNotFoundException {
        return new ResponseEntity<User>(userRepository.save(user), HttpStatus.ACCEPTED);

    }

    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());

            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);

    }

    // --------------------------------------------------------------------------------------

    @Override
    public ResponseEntity<Object> login(User user) throws UserNotFoundException {

        return null;
    }

    @Override
    public ResponseEntity<Object> logout(User user) throws UserNotFoundException {

        return null;
    }

}
