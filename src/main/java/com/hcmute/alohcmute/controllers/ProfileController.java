package com.hcmute.alohcmute.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    
    @GetMapping("/profile")
    public String getProfile() {
        return "profile/profile";
    }
}
