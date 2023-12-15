package com.hcmute.alohcmute.apicontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcmute.alohcmute.entity.Message;
import com.hcmute.alohcmute.service.MessageService;

@RestController
@RequestMapping("/api/message")
public class ApiMessageConversation {
    
    @Autowired
    private MessageService messageService;

    @PostMapping("/send/{conversationId}/{senderId}")
public ResponseEntity<Message> sendMessage(
        @PathVariable Long conversationId,
        @PathVariable Long senderId,
        @RequestBody Message message
) {
    Message newMessage = messageService.sendMessage(conversationId, senderId, message);
    if (newMessage != null) {
        return new ResponseEntity<>(newMessage, HttpStatus.CREATED);
    } else {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
}
