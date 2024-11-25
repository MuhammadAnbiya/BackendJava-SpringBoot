package com.chat.models.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.models.entities.User;

public interface UserRepo extends JpaRepository<User, String> {

    Optional<User> findByUsename(String username);

    
}
