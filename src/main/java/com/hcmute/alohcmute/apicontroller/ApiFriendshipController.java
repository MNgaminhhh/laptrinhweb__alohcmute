package com.hcmute.alohcmute.apicontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcmute.alohcmute.entity.Friendship;
import com.hcmute.alohcmute.enums.FriendshipStatus;
import com.hcmute.alohcmute.service.FriendshipService;


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
        List<Friendship> friendships = friendshipService.getFriendshipOfUser(userId, status);
        System.out.println("aaaaaaa"+status.name());
        return new ResponseEntity<>(friendships, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Friendship> addFriendship(@RequestBody Friendship friendship) {
        Friendship newFriendship = friendshipService.addFriendship(friendship);
        return new ResponseEntity<>(newFriendship, HttpStatus.CREATED);
    }

    @PostMapping("/edit/{friendshipId}")
    public ResponseEntity<Friendship> editFriendship(@PathVariable Long id, @RequestBody Friendship friendship) {
        Friendship newFriendship = friendshipService.editFriendship(id, friendship);
        return new ResponseEntity<>(newFriendship, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{friendshipId}")
    public ResponseEntity<Void> deleteFriendship(@PathVariable Long id) {
        friendshipService.deleteFriendship(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
