package com.hcmute.alohcmute.apicontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcmute.alohcmute.entity.Message;
import com.hcmute.alohcmute.service.MessageService;

@RestController
@RequestMapping("/api/message")
public class ApiMessageController {

    @Autowired
    private MessageService messageService;
    
    // @GetMapping("/")
    // public ResponseEntity<List<Message>> findMessage(@RequestParam (value = "senderId") Long senderId, @RequestParam (value = "reiceiverId") Long receiverId) {
    //     List<Message> messages =  messageService.findMessage(senderId, receiverId);
    //     return new ResponseEntity<>(messages, HttpStatus.OK);
    // }
}
