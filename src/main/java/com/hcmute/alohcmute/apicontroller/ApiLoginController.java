package com.hcmute.alohcmute.apicontroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.hcmute.alohcmute.Dto.AuthRequest;
import com.hcmute.alohcmute.entity.PasswordResetToken;
import com.hcmute.alohcmute.entity.User;
import com.hcmute.alohcmute.service.EmailService;
import com.hcmute.alohcmute.service.JwtService;
import com.hcmute.alohcmute.service.PasswordResetTokenService;
import com.hcmute.alohcmute.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class ApiLoginController {
    
    @Autowired
    private UserService service;
    @Autowired
    private PasswordResetTokenService passwordResetTokenService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtService jwtService; 

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/addNewUser") 
    public String addNewUser(@RequestBody User userInfo) { 
        return service.addUser(userInfo); 
        
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
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        try {
            User user = service.getUserByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found."));

            String token = passwordResetTokenService.createPasswordResetToken(user);

            emailService.sendPasswordResetEmail(user.getEmail(), token);

            return ResponseEntity.ok("Password reset email sent successfully.");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to initiate password reset.");
        }
    }
}
