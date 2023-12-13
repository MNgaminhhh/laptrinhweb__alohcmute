package com.hcmute.alohcmute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcmute.alohcmute.entity.Message;
import com.hcmute.alohcmute.repository.MessageRepository;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;

    // public List<Message> findMessage(Long senderId, Long receiverId) {
    //     return messageRepository.findMessage(senderId, receiverId);
    // }
}
