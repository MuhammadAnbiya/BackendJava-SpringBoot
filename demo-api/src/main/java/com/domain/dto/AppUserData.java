package com.domain.dto;

public class AppUserData {

    private String fullname;
    private String email;
    private String password;
    private String appUserRole;
    
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAppUserRole() {
        return appUserRole;
    }
    public void setAppUserRole(String appUserRole) {
        this.appUserRole = appUserRole;
    }

}
