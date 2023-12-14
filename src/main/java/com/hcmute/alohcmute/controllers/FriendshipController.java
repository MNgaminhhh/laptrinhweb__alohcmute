package com.hcmute.alohcmute.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/friendships")
public class FriendshipController {

    @GetMapping("/friend/{userId}")
    public String showFriend(Model model, @PathVariable (value = "userId") Long userID) {
        model.addAttribute("topic", "friend");
        return "friendship/friendship";
    }

    @GetMapping("friend_requested/{userId}")
    public String showFriendRequested(Model model, @PathVariable (value = "userId") Long userId) {
        model.addAttribute("userId", userId);
        model.addAttribute("topic", "requested");
        return "friendship/friendship";
    }

    @GetMapping("add_friend/{userId}")
    public String showUserCanBeFriend(Model model, @PathVariable (value = "userId") Long userId) {
        model.addAttribute("userId", userId);
        model.addAttribute("topic", "add_friend");
        return "friendship/friendship";
    }
    @GetMapping("/friend/profile/{userId}")
    public String showUserProfileFriend(Model model, @PathVariable(value = "userId") Long userId) {
        return "user/profileuserfriend";
    }
}
