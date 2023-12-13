package com.hcmute.alohcmute.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.access.prepost.PreAuthorize; 
import org.springframework.web.bind.annotation.*; 
@Controller
public class UserController {

    @GetMapping("/users")
    public String showUsers(Model model) {
        return "user/alluser";
    }

    @GetMapping("/user/profile") 
    @PreAuthorize("hasAuthority('ROLE_USER')") 
    public String userProfile() { 
        return "user/profileuser"; 
    } 
    @GetMapping("/user/profile/{userId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile(@PathVariable Long userId, Model model) {
        return "user/profileuser";
    }
}
