package com.hcmute.alohcmute.apicontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcmute.alohcmute.entity.Friendship;
import com.hcmute.alohcmute.service.FriendshipService;

@RestController
@RequestMapping("/api/friendship")
public class ApiFriendshipController {
    private final FriendshipService friendshipService;

    public ApiFriendshipController(FriendshipService friendshipService){
        this.friendshipService = friendshipService;
    }

    @GetMapping
    public ResponseEntity<List<Friendship>> getAllFriendship() {
        List<Friendship> friendships = friendshipService.getAllFriendship();
        return new ResponseEntity<>(friendships, HttpStatus.OK);
    }
    
}
