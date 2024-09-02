package com.domain.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.domain.services.AppUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig { // WebSecurityConfigurAdapter = sudah deprecated
    
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // @Override
    // protected void configure(HttpSecurity auth) throws exception {   ==> versi WebSecurityConfigurAdapter
    //     http.csrf().disable()
    //     .authorizeRequest().antMatchers("api/users/register").permitAll()
    //     .anyRequest().fullyAuthenticated()
    //     .and().httpBasic();
    // }

    @SuppressWarnings("removal") // menonaktifkan peringatan kompiler tertentu disini (removal)

    // Membuat Bean untuk SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Menghilangkan CSRF
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/register").permitAll() // Membuat API itu bisa diakses tanpa Authentication
                .anyRequest().authenticated() // selain API itu harus melalui tahap Authentication
            )
            .httpBasic(); // Mengaktifkan HTTP Basic Authentication

        return http.build();
    }

    // Membuat Bean untuk AuthenticationManager
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        // AuthenticationManagetBuild untuk mengembalikan autentikasi yang berhasil atau memunculkan pengecualian jika gagal.
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        //Dao Authentication Provider untuk mengecek username dan password
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws exception {
    //     auth.authenticationProvider(daoAuthenticationProvider());
    // }

    // @Bean
    // public DaoAuthenticationProvider daoAuthenticationProvider(){
    //     DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    //     provider.setPasswordEncoder(bCryptPasswordEncoder);
    //     provider.setUserDetailsService(appUserService);
    //     return provider;
        
    // }

}

