package com.chat.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.models.MessageStatus;
import com.chat.models.entities.ChatMessage;
import com.chat.models.repository.ChatMessageRepo;
import com.chat.utils.ResourceNotFoundException;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepo chatMessageRepo;

    @Autowired
    private ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setId(UUID.randomUUID().toString());
        chatMessage.setStatus(MessageStatus.RECEIVED);
        return chatMessageRepo.save(chatMessage);
    }

    public Long countNewMessages(String senderId, String recipientId) {
        return chatMessageRepo.countBySenderIdAndRecipientIdAndStatus(senderId, recipientId, MessageStatus.RECEIVED);
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        Optional<String> chatId = chatRoomService.getChatId(senderId, recipientId, false);
        List<ChatMessage> messages = chatId.map(cid -> chatMessageRepo.findByChatId(cid))
                                           .orElse(new ArrayList<>());

        if (!messages.isEmpty()) {
            chatMessageRepo.updateStatus(MessageStatus.DELIVERED, senderId, recipientId);
        }

        return messages;
    }

    public ChatMessage findById(String id) {
        return chatMessageRepo.findById(id)
                              .map(chatMessage -> {
                                  chatMessage.setStatus(MessageStatus.DELIVERED);
                                  return chatMessageRepo.save(chatMessage);
                              })
                              .orElseThrow(() -> new ResourceNotFoundException("Pesan tidak ditemukan"));
    }
}
