package com.hcmute.alohcmute.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostController {
    @GetMapping("/post/{postId}")
    public String showSinglePost(@PathVariable Long postId, Model model) {
        return "singlepage/page";
    }
}
