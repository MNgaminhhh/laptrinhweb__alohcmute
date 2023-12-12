package com.hcmute.alohcmute.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
