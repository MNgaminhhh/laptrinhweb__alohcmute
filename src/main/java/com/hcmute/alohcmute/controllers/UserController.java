package com.hcmute.alohcmute.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hcmute.alohcmute.entity.User;
import com.hcmute.alohcmute.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    @GetMapping("/users")
    public String showUsers(Model model) {
        return "users";
    }
}
