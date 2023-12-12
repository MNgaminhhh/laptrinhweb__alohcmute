package com.hcmute.alohcmute.apicontroller;

import java.util.List;
import java.util.Optional;

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

import com.hcmute.alohcmute.entity.Profile;
import com.hcmute.alohcmute.enums.FriendshipStatus;
import com.hcmute.alohcmute.service.ProfileService;



@RestController
@RequestMapping("/api/profile")
public class ApiProfileController {
    
    private ProfileService profileService;
    public ApiProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Profile> getProfielById(@PathVariable Long userId) {
        Optional<Profile> profile = profileService.getProfileUser(userId);
        return profile.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{userId}/notfriend")
    public ResponseEntity<List<Profile>> getProfileUserNotBeFriend(@PathVariable Long userId) {
        List<Profile> profile = profileService.getProfileUserNotBeFriend(userId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
    
    @GetMapping("/{userId}/")
    public ResponseEntity<List<Profile>> getProfileFriend(@PathVariable Long userId, @RequestParam FriendshipStatus status) {
        List<Profile> profile = profileService.getProfileFriend(userId, status);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long userId) {
        profileService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Profile> editProfile(@PathVariable Long userId, @RequestBody Profile profile) {
        Profile newProfile = profileService.eidtProfile(userId, profile);
        return new ResponseEntity<>(newProfile, HttpStatus.CREATED);
    }

}
