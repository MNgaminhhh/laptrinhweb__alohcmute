package com.hcmute.alohcmute.apicontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcmute.alohcmute.entity.Friendship;
import com.hcmute.alohcmute.enums.FriendshipStatus;
import com.hcmute.alohcmute.service.FriendshipService;

import jakarta.websocket.server.PathParam;


@RestController
@RequestMapping("/api/friendship")
public class ApiFriendshipController {
    private final FriendshipService friendshipService;

    public ApiFriendshipController(FriendshipService friendshipService){
        this.friendshipService = friendshipService;
    }

    @GetMapping("/{userId}/")
    public ResponseEntity<List<Friendship>> getFriendshipOfUser(@PathVariable Long userId, @RequestParam FriendshipStatus status)
    {
        List<Friendship> friendships = friendshipService.getFriendshipOfUser(userId, status);;
        return new ResponseEntity<>(friendships, HttpStatus.OK);
    }

    @GetMapping("/relationship/")
    public ResponseEntity<Optional<Friendship>> getRelationship(@PathParam ("userId1") Long userId1, @PathParam ("userId2") Long userId2) {
        Optional<Friendship> friendship = friendshipService.findRelationship(userId1, userId2);
        return new ResponseEntity<>(friendship, HttpStatus.OK);
    }
    
    @PostMapping("/")
    public ResponseEntity<Friendship> addFriendship(@PathParam ("userId1") Long userId1, @PathParam ("userId2") Long userId2,@RequestBody Friendship friendship) {
        Friendship newFriendship = friendshipService.addFriendship(userId1, userId2, friendship);
        if (newFriendship != null) {   
            return new ResponseEntity<>(newFriendship, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit-status/")
    public ResponseEntity<Friendship> updateStatus(@PathParam("userId1") Long userId1, @PathParam("userId2") Long userId2, @PathParam("status") FriendshipStatus status) {
        Friendship newFriendship = friendshipService.updateStatus(userId1, userId2, status);
        if (newFriendship != null) {
            return new ResponseEntity<>(newFriendship, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/")
    public ResponseEntity<Void> deleteFriendship(@PathParam("userId1") Long userId1, @PathParam("userId2") Long userId2) {
        friendshipService.deleteFriendship(userId1, userId2);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
