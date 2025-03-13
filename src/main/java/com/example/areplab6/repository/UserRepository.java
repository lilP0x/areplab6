package com.example.areplab6.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.areplab6.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}