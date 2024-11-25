package com.chat.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chat.models.MessageStatus;
import com.chat.models.entities.ChatMessage;

public interface ChatMessageRepo extends JpaRepository<ChatMessage, String> {
    
    Long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, MessageStatus status);
    
    List<ChatMessage> findByChatId(String chatId);

    @Modifying
    @Query("UPDATE ChatMessage c SET c.status = :status WHERE c.senderId = :senderId AND c.recipientId = :recipientId")
    int updateStatus(
            @Param("status") MessageStatus status,
            @Param("senderId") String senderId,
            @Param("recipientId") String recipientId);
}
