package com.hcmute.alohcmute.component;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hcmute.alohcmute.entity.User;
import com.hcmute.alohcmute.repository.UserRepository;

import java.time.LocalDateTime;

@Component
public class DefaultAdminLoader {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultAdminLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadDefaultAdmin() {
        if (!userRepository.existsByUsername("admin1")) {
            User adminUser = new User();
            adminUser.setUsername("admin1");
            adminUser.setEmail("admin@gmail.com");
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setIsAdmin("ROLE_ADMIN");
            adminUser.setCreatedAt(LocalDateTime.now());
            userRepository.save(adminUser);
        }
    }
}
