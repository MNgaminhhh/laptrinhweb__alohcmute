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
    

    @GetMapping("/user/userProfile") 
    @PreAuthorize("hasAuthority('ROLE_USER')") 
    public String userProfile() { 
        return "Welcome to User Profile"; 
    } 
  
    @GetMapping("/admin/adminProfile") 
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") 
    public String adminProfile() { 
        return "Welcome to Admin Profile"; 
    } 
    
}
