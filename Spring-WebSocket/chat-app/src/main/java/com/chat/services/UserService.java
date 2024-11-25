package com.chat.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.models.entities.User;
import com.chat.models.repository.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo userRepo;

    public User findByUsername(String username){
        Optional<User> optional = userRepo.findByUsername(username);
        if (optional.isPresent())
            return optional.get();

        return null;
    }

    public User create(User user){
        user.setId(UUID.randomUUID().toString());
        return userRepo.save(user);
    }
}
