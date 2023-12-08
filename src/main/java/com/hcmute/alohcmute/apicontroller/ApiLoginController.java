package com.hcmute.alohcmute.apicontroller;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hcmute.alohcmute.entity.User;
import com.hcmute.alohcmute.service.UserService;

@RestController
@RequestMapping("/api")
public class ApiLoginController {
    private UserService userService;

    public ApiLoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        Optional<User> existingUser = userService.getUserByUserName(user.getUsername());
        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
