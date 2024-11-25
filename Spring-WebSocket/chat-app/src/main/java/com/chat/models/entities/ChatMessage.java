package com.chat.models.entities;

import java.io.Serializable;
import java.util.Date;

import com.chat.models.MessageStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ChatMessage implements Serializable{

    @Id
    private String id;
    private String chatId;
    private String recipientId;
    private String recipientName;
    private String senderId;
    private String senderName;
    private String content;
    private Date createAt;
    @Enumerated(EnumType.STRING)
    private MessageStatus status;

}
