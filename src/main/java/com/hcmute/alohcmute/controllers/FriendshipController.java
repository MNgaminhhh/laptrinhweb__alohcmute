package com.hcmute.alohcmute.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcmute.alohcmute.entity.Friendship;
import com.hcmute.alohcmute.entity.Profile;
import com.hcmute.alohcmute.enums.FriendshipStatus;
import com.hcmute.alohcmute.service.FriendshipService;
import com.hcmute.alohcmute.service.ProfileService;

@Controller
@RequestMapping("/friendships")
public class FriendshipController {
    
    @Autowired
    private FriendshipService friendService;
    
    @Autowired
    private ProfileService profileService;

    @GetMapping("/friend/{userId}")
    public String showFriend(Model model, @PathVariable (value = "userId") Long userID) {
        FriendshipStatus status = FriendshipStatus.ACCEPTED;
        List<Friendship> listFriend =friendService.getFriedshipOfUser(userID, status);
        List<Optional<Profile>> profiles = new ArrayList<>();
        for (Friendship friendship : listFriend) {
            Long user2Id = friendship.getUser2().getUserId();
            Optional<Profile> profileUser = profileService.getProfileUser(user2Id);
            if (profileUser.isPresent()) {
                profiles.add(profileUser);
            }
        }
        model.addAttribute("all_friends", listFriend);
        model.addAttribute("user2_profile", profiles);
        model.addAttribute("length1", profiles.size());
        model.addAttribute("type_friendship", "ACCEPTED");
        return "friendship/friendship";
    }

    @GetMapping("friend_requested/{userId}")
    public String showFriendRequested(Model model, @PathVariable (value = "userId") Long userId) {
        FriendshipStatus status = FriendshipStatus.REQUESTED;
        List<Friendship> listFriend =friendService.getFriedshipOfUser(userId, status);
        List<Optional<Profile>> profiles = new ArrayList<>();
        for (Friendship friendship : listFriend) {
            Long user2Id = friendship.getUser2().getUserId();
            Optional<Profile> profileUser = profileService.getProfileUser(user2Id);
            if (profileUser.isPresent()) {
                profiles.add(profileUser);
            }
        }
        model.addAttribute("test", userId);
        model.addAttribute("all_requested", listFriend);
        model.addAttribute("user2_profile", profiles);
        model.addAttribute("length2", profiles.size());
        model.addAttribute("type_friendship", "REQUESTED");
        return "friendship/friendship";
    }
}
