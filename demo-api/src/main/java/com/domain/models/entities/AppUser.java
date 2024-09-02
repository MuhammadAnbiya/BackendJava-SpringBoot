package com.domain.models.entities;

import java.util.Collection;
import java.util.Collections;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "tbl_users")
// AppUser mengimplementasikan UserDetails, yang merupakan bagian dari Spring Security.
public class AppUser implements UserDetails {  

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150, nullable = false)
    private String fullname;
    @Column(length = 150, nullable = false, unique = true)
    private String email;

    // kalau error muncul status 500 karena belum di custom error handling nya
    @Column(length = 200, nullable = false) 
    private String password;

    // tipe data khusus yang berisi sekumpulan konstanta yang telah ditentukan sebelumnya
    @Enumerated((EnumType.STRING)) 
    private AppUserRole appUserRole;

    
    // implemented method dari UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority); // Intinya dia ngecek kalau yang masuk itu admin atau user dari appUserRole
    }

    @Override
    public String getPassword() { // untuk keperluan authentikasi untuk mengembalikan kata sandi pengguna
        return password;
    }

    @Override
    public String getUsername() { // untuk keperluan authentikasi untuk mengembalikan username pengguna
        return email;
    }

    // class yang digunakan oleh Spring Security untuk mengecek apakah akun kadaluarsa atau tidak
    @Override
    public boolean isAccountNonExpired() { 
        return  true; // karena return nya true maka akun tidak akan pernah kadaluarsa
    }

    // class yang digunakan oleh Spring Security untuk mengecek apakah akun di terkunci
    @Override
    public boolean isAccountNonLocked() { 
        return true; // karena return nya true maka akun tidak akan pernah terkunci
    }

    // class yang digunakan oleh Spring Security untuk mengecek apakah kredensial (misal sandi) kadaluarsa atau tidak
    @Override 
    public boolean isCredentialsNonExpired() { 
        return true; // karena return nya true maka kredensial nya nya tidak akan kadaluarsa
    }

    // class yang digunakan oleh Spring Security untuk mengecek apakah akun pengguna aktif
    @Override 
    public boolean isEnabled() { 
        return true; // karena return nya true maka akun pengguna akan selalu di aktifkan
    }

    //Setter Getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AppUserRole getAppUserRole() {
        return appUserRole;
    }

    public void setAppUserRole(AppUserRole appUserRole) {
        this.appUserRole = appUserRole;
    }    
}
