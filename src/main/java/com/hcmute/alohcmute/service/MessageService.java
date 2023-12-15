package com.hcmute.alohcmute.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcmute.alohcmute.entity.Conversation;
import com.hcmute.alohcmute.entity.Message;
import com.hcmute.alohcmute.entity.User;
import com.hcmute.alohcmute.repository.ConversationRespository;
import com.hcmute.alohcmute.repository.MessageRepository;
import com.hcmute.alohcmute.repository.UserRepository;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversationRespository conversationRespository;

    public Message sendMessage(Long conversationId, Long senderId, Message newMessage) {
        Optional<Conversation> conversation = conversationRespository.findById(conversationId);
        Optional<User> sender = userRepository.findById(senderId);
        if (conversation.isPresent() && sender.isPresent()) {
            newMessage.setConversation(conversation.get());
            newMessage.setSender(sender.get());
            return messageRepository.save(newMessage);
        }
        else {
            return null;
        }
    }
}
