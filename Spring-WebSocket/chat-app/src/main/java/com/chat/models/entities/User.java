package com.chat.models.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User implements Serializable{

    @Id
    private String id;
    private String username;
    private String password;
    private String authority;
    private String namaLengkap;

}
