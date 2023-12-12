package com.hcmute.alohcmute.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LikeController {
    @GetMapping("/likes")
    
    public String showLikes(Model model) {
        return "like/alllike"; }   
}
