package com.hcmute.alohcmute.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcmute.alohcmute.entity.PasswordResetToken;
import com.hcmute.alohcmute.entity.User;
import com.hcmute.alohcmute.repository.PasswordResetTokenRepository;

@Service
public class PasswordResetTokenService {
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private EmailService emailService;

    public String createPasswordResetToken(User user) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expiryDate = LocalDateTime.now().plusHours(1);

        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiryDate(expiryDate);

        passwordResetTokenRepository.save(passwordResetToken);

        sendPasswordResetEmail(user.getEmail(), token);

        return token;
    }

    public void processPasswordReset(String token, String newPassword) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);

        if (passwordResetToken != null && !passwordResetToken.isExpired()) {
            User user = passwordResetToken.getUser();
            user.setPassword(newPassword);
            passwordResetTokenRepository.delete(passwordResetToken);
        } else {
            throw new RuntimeException("Invalid or expired token");
        }
    }

    private void sendPasswordResetEmail(String email, String token) {
            emailService.sendPasswordResetEmail(email, token);
    }
}
