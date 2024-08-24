package com.domain.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/users")
public class AppUserController {
    
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<AppUser>> register(@RequestBody AppUserData userData){

        ResponseData<AppUser> response = new ResponseData<>();
        AppUser appUser = modelMapper.map(useData, AppUser.class);
        response.setPayload(appUserService.registerAppUser(appUser));
        response.setStatus(true);
        response.getMassage().add("AppUser saved!");
        return ResponseEntity.ok(response);
    }
}
