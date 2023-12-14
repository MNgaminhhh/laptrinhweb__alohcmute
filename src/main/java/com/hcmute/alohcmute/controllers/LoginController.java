package com.hcmute.alohcmute.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String showLogin(Model model) {
        return "login/login";
    }
    @GetMapping("/forgot-password")
    public String forgotLogin(Model model) {
        return "login/forgotpassword";
    }
    @GetMapping("/reset-password")
    public String showResetPassword(@RequestParam(name = "token", required = false) String token, Model model) {
        model.addAttribute("token", token);
        return "login/resetpassword"; // Đặt tên trang đặt lại mật khẩu của bạn ở đây
    }

    // http://localhost:1999/reset-password?token=d863987c-d4f7-4f34-8225-1f1f5389402f
}
