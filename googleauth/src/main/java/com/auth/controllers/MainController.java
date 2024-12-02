package com.auth.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    
    @RequestMapping("/")
    public String home(){
        return "Welcome to Dashboard!";
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
