package com.chat.models.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chat.models.entities.ChatRoom;

public interface ChatRoomRepo extends JpaRepository<ChatRoom, String>{
    
    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
    
}
