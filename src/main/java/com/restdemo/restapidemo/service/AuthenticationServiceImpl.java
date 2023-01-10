package com.restdemo.restapidemo.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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

        // -------------------------------------------
        boolean _isActive = isActive();

        // -------------------------------------------

        byte[] _salt_value = getSalt();

        String saltValue = _salt_value.toString();

        userFromClient.setSalt(saltValue);
        userFromClient.setActive(_isActive);

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
    public User getSingleUser(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent())
            throw new UserNotFoundException();

        return user.get();
    }

    public User updateUser(Long userId, User user) throws UserNotFoundException {


        User userInDb = this.userRepository.findById(userId).get();

        if (Objects.nonNull(user.getFirstName()) &&
                !"".equalsIgnoreCase(user.getFirstName())) {
            userInDb.setFirstName(user.getFirstName());
        }

        if (Objects.nonNull(user.getLastName()) &&
                !"".equalsIgnoreCase(user.getLastName())) {
            userInDb.setLastName(user.getLastName());

        }

        if (Objects.nonNull(user.getPassword()) &&
                !"".equalsIgnoreCase(user.getPassword())) {
            userInDb.setEmail(user.getPassword());
        }

        if (Objects.nonNull(user.getEmail()) &&
                !"".equalsIgnoreCase(user.getEmail())) {
            userInDb.setEmail(user.getEmail());
        }

        return this.userRepository.save(userInDb);

    }



    @Override
    public ResponseEntity<Object> login(User user) throws UserNotFoundException {

        return null;
    }

    @Override
    public ResponseEntity<Object> logout(User user) throws UserNotFoundException {

        return null;
    }

    @Override
    public List<User> getAllUsers() throws UserNotFoundException {

        Iterable<User> userInDb = this.userRepository.findAll();

        if (userInDb == null)
            throw new UserNotFoundException();

        return convertUsersToList(userInDb);
    }

    private List<User> convertUsersToList(Iterable<User> users) {
        return StreamSupport.stream(users.spliterator(), false)
                .collect(Collectors.toList());

    }

    // @Override
    // public void deleteUser(Long userId) {

    // this.userRepository.deleteById(userId);

    // // TODO Auto-generated method stub

    // }

    @Override
    public boolean isActive() {

        return true;
        // TODO Auto-generated method stub
        // return null;
    }

    // ------------------------------------------------------------------------------------------
    @Override
    public User deleteUser(Long userId, User user) throws UserNotFoundException {

        User userInDb = this.userRepository.findById(userId).get();

        if (Objects.nonNull(user.isActive())) {
            userInDb.setActive(notActive());
        }
        // TODO Auto-generated method stub
        return null;

    }
    // -----------------------------------------------------------------------------------------

    @Override
    public boolean notActive() {

        System.out.println(" null");
        // TODO Auto-generated method stub
        return false;

    }

}
