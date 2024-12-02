package com.googleauth.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping("/")
    public String home(){
        return "Welcome to Dashboard!";
    }

    @RequestMapping("/home")
    public Principal user(Principal user){
        return user;
    }
    
}
