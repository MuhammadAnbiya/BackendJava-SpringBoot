// package com.domain.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// import com.domain.services.AppUserService;

// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
//     @Autowired
//     private AppUserService appUserService;

//     @Autowired
//     private BCryptPasswordEncoder bCryptPasswordEncoder;

//     @Override
//     protected void configure(HttpSecurity auth) throws exception {
//         http.csrf().disable()
//         .authorizeRequest().antMatchers("api/users/register").permitAll()
//         .anyRequest().fullyAuthenticated()
//         .and().httpBasic();
//     }

//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws exception {
//         auth.authenticationProvider(daoAuthenticationProvider());
//     }

//     @Bean
//     public DaoAuthenticationProvider daoAuthenticationProvider(){
//         DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//         provider.setPasswordEncoder(bCryptPasswordEncoder);
//         provider.setUserDetailsService(appUserService);
//         return provider;
        
//     }

// }
