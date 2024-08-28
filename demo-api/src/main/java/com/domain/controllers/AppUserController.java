package com.domain.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.domain.dto.AppUserData;
import com.domain.dto.ResponseData;
import com.domain.models.entities.AppUser;
import com.domain.services.AppUserService;

import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api/users")
public class AppUserController {
    
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ModelMapper modelMapper;

    // Endpoint untuk melakukan registrasi user
    @PostMapping("/register")
    public ResponseEntity<ResponseData<AppUser>> register(@RequestBody AppUserData userData){

        ResponseData<AppUser> response = new ResponseData<>(); // instance dari kelas ResponseData

        AppUser appUser = modelMapper.map(userData, AppUser.class); // Pemetaan data dengan ModelMapper Bean
        response.setPayload(appUserService.registerAppUser(appUser));
        response.setStatus(true);
        response.getMessages().add("AppUser saved!");
        return ResponseEntity.ok(response);
    }
}
