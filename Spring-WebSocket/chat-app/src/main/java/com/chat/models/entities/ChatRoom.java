package com.chat.models.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ChatRoom implements Serializable{

    @Id
    private String id;
    private String chatId;
    private String recipientId;
    private String senderId;
    

}
