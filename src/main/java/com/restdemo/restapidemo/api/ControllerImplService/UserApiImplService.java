package com.restdemo.restapidemo.api.ControllerImplService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.restdemo.restapidemo.model.User;
import com.restdemo.restapidemo.repository.UserRepository;

@Service
public class UserApiImplService {

    private UserRepository userRepository;

    public ResponseEntity<Object> signupImpl(@RequestBody User UserFromCient) {

        List<User> userInDb = StreamSupport.stream(this.userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        for (User user : userInDb) {
            if (user.getEmail().equals(UserFromCient.getEmail())) {
                return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("User has already signed up");
            }
        }

        User savedUser = this.userRepository.save(UserFromCient);

        return ResponseEntity.ok().body(savedUser);
    }

}
