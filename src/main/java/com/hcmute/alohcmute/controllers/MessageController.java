package com.hcmute.alohcmute.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/message")
public class MessageController {
    
    // @GetMapping("/{userId}")
    // public String getMessage(Model model, @PathVariable Long userId) {
    //     model.addAttribute("userId", userId);
    //     return "message/message";
    // }
}
