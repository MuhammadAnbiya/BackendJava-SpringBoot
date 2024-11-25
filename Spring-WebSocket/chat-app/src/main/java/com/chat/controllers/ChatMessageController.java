package com.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chat.services.ChatMessageService;


@Controller
@RequestMapping("/api")
public class ChatMessageController {
    
    @Autowired
    private ChatMessageService chatMessageService;

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
        @PathVariable("sernderId") String senderId,
        @PathVariable("recipientId") String recipientId
    ) {
        return ResponseEntity.ok(chatMessageService.countNewMessages(senderId, recipientId));

    }

    @GetMapping("messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessage(
            @PathVariable("senderID") String senderId,
            @PathVariable("recipientId") String recipientId) {
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
    }
    
    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage(@PathVariable("id") String id) {
        return ResponseEntity.ok(chatMessageService.findById(id));
    }

    
}
