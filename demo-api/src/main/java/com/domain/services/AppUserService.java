package com.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.domain.models.repos.AppUserRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AppUserService implements UserDetailsService{

    @Autowired
    private AppUserRepo appUserRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepo.findByEmail(email)
            .orElseThrow(() -> 
            new UsernameNotFoundException(
                String.format("user with email '%s' not found", email)));
    }

    
}
