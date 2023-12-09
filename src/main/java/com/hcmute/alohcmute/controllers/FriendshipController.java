package com.hcmute.alohcmute.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FriendshipController {
    
    @GetMapping("/friendships")
    public String showFriendShip(Model model) {
        return "friendship/allfriendship";
    }
}
