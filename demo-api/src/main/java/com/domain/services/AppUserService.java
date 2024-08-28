package com.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.domain.models.entities.AppUser;
import com.domain.models.repos.AppUserRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AppUserService implements UserDetailsService{

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { // Method Implementasi dari UserDetailsService
        return appUserRepo.findByEmail(email) // ini akan mencari user berdasarkan email nya, karena email pasti unik dan berbeda
            .orElseThrow(() -> 
            new UsernameNotFoundException( // jika email saat login tidak terdaftar maka akan error UsernameNotFoundException
                String.format("user with email '%s' not found", email))); // dan menampilkan string format ini
    }

    public AppUser registerAppUser(AppUser user){ // method untuk mengecek apakah email yang di registrasi sudah ada atau belum
        boolean userExists = appUserRepo.findByEmail(user.getEmail()).isPresent(); // memberikan logic apakah email sudah ada atau belum (true/flase)
        if(userExists){ // kalau false
            throw new RuntimeException(
                String.format("user with email '%s' already exists", user.getEmail())); // maka akan menampilkan error runtimeException
        }
        // kalau true
    String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword()); //password yang didaftarkan akan di encode
    user.setPassword(encodedPassword);
    return appUserRepo.save(user); // lalu baru disimpan kedalam database

    }


}
