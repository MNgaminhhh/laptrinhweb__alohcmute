package com.hcmute.alohcmute.apicontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcmute.alohcmute.entity.Conversation;
import com.hcmute.alohcmute.service.ConversationService;

@RestController
@RequestMapping("/api/conversation/")
public class ApiConversationController {
    
    @Autowired
    private ConversationService conversationService;

    @GetMapping
    public ResponseEntity<List<Conversation>> findAll() {
        return new ResponseEntity<>(conversationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/by-user/")
    public ResponseEntity<Optional<Conversation>> getConversationOf(@RequestParam("member1") Long member1, @RequestParam ("member2") Long member2) {
        Optional<Conversation> conversation = conversationService.findConversationsOf(member1, member2);
        if (conversation.isPresent()) {
            return new ResponseEntity<>(conversation, HttpStatus.OK);
        }
        else 
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Conversation> createConversation(@RequestParam("member1") Long member1, @RequestParam ("member2") Long member2, @RequestBody Conversation conversation) {
        Conversation newConConversation = conversationService.addConversation(member1, member2, conversation);
        if (newConConversation!=null) {
            return new ResponseEntity<>(newConConversation, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
