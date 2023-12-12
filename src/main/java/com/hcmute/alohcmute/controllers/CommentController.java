package com.hcmute.alohcmute.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommentController {
    @GetMapping("/comments")

    public String showComments(Model model) {
         return "comment/allcomment"; }
}
