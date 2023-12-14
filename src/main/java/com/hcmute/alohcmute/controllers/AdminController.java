package com.hcmute.alohcmute.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
public class AdminController {
    @GetMapping("/admin") 
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") 
    public String adminProfile() { 
        return "admin/page"; 
    } 
    @GetMapping("/admin/users") 
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") 
    public String adminUsers() { 
        return "admin/users"; 
    } 
    @GetMapping("/admin/users/profiles/{userId}") 
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") 
    public String adminUsersProfile(@PathVariable Long userId, Model model) { 
        return "admin/adminprofile"; 
    }
    @GetMapping("/admin/users/{userId}") 
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") 
    public String adminUsers(@PathVariable Long userId, Model model) { 
        return "admin/adminprofileuser"; 
    }
    @GetMapping("/admin/users/edit/{userId}") 
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") 
    public String adminEditUsers(@PathVariable Long userId, Model model) { 
        return "admin/edituser"; 
    }
}
