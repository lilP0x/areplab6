package com.example.areplab6.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.areplab6.model.User;
import com.example.areplab6.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username); 
    }

    public void registerUser(String username, String password){
        if (userRepository.findByUsername(username) != null) {

            userRepository.save(new User(username, passwordEncoder.encode(password)));
        }
    }

}
