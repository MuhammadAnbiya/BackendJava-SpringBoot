package com.example.spring_api.service;

import com.example.spring_api.api.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    
    private List<User> userList;

    public UserService() {
        userList = new ArrayList<>();

        User user1 = new User(1, "anbiya1", 191, "anbiya17agustus1@gmail.com");
        User user2 = new User(2, "anbiya2", 192, "anbiya17agustus2@gmail.com");
        User user3 = new User(3, "anbiya3", 193, "anbiya17agustus3@gmail.com");
        User user4 = new User(4, "anbiya4", 194, "anbiya17agustus4@gmail.com");
        User user5 = new User(5, "anbiya5", 195, "anbiya17agustus5@gmail.com");

        userList.addAll(Arrays.asList(user1,user2,user3,user4,user5));
    }


    public Optional<User> getUser(Integer id) {
        Optional optional = Optional.empty();
        for (User user: userList){
            if(id == user.getId()){
                optional = Optional.of(user);
                return optional;
            }
        }
        return optional;
    }
}
