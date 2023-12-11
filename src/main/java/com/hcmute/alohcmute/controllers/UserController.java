package com.hcmute.alohcmute.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication; 
import org.springframework.security.authentication.AuthenticationManager;

import com.hcmute.alohcmute.Dto.AuthRequest;
import com.hcmute.alohcmute.entity.User;
import com.hcmute.alohcmute.service.JwtService;
import com.hcmute.alohcmute.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize; 
import org.springframework.web.bind.annotation.*; 
@Controller
@RequestMapping("/auth") 
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtService jwtService; 

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/users")
    public String showUsers(Model model) {
        return "user/alluser";
    }
    @GetMapping("/login")
    public String showLogin(Model model) {
        return "login/login";
    }


    @GetMapping("/welcome") 
    public String welcome() { 
        return "user/alluser";
    } 
  
    @PostMapping("/addNewUser") 
    public String addNewUser(@RequestBody User userInfo) { 
        return service.addUser(userInfo); 
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
    @PostMapping("/generateToken")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(authRequest.getUsername());
                Cookie userCookie = new Cookie("username", authRequest.getUsername());
                userCookie.setMaxAge(30 * 60);
                userCookie.setHttpOnly(true);
                userCookie.setPath("/");
                response.addCookie(userCookie);

                Cookie cookie = new Cookie("jwtToken", token);
                cookie.setMaxAge(30 * 60);
                cookie.setHttpOnly(true);
                cookie.setPath("/");
                response.addCookie(cookie);
                
                return ResponseEntity.ok(token);
            } else {
                throw new UsernameNotFoundException("Invalid user credentials!");
            }
        } catch (Exception e) {
            // Handle authentication failure
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
