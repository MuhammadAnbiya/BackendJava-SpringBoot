// package com.chat.models;

// import java.util.Collection;
// import java.util.Collections;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import com.chat.models.entities.User;

// public class UserDetailsImpl implements UserDetails{
    
//     private final User user;

//     public UserDetailsImpl(User user){
//         this.user = user;
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> grantedAuthorities() (){
//         return Collections.singletonList(new SimpleGrantedAuthority(user.getAuthority()));
//     }

//     @Override
//     public String getPassword() {
//         return user.getPassword();
//     }

//     @Override
//     public String getUsername(){
//         return user.getUsername();
//     }

//     @Override
//     public boolean isAccountNonExpired(){
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }
    
    
// }
