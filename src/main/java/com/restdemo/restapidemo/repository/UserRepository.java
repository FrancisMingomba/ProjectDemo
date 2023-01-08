package com.restdemo.restapidemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

import com.restdemo.restapidemo.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByEmail(String email);

    ResponseEntity<User> save(Long userId);

}
