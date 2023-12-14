package com.hcmute.alohcmute.apicontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.hcmute.alohcmute.Dto.UpdateProfileRequest;
import com.hcmute.alohcmute.entity.Profile;
import com.hcmute.alohcmute.enums.FriendshipStatus;
import com.hcmute.alohcmute.service.ProfileService;

@RestController
@RequestMapping("/api/profile")
public class ApiProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileService.findAllProfiles();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long userId) {
        Optional<Profile> profile = profileService.findProfileById(userId);
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

    @PostMapping("/{userId}")
    public ResponseEntity<Profile> createProfile(@PathVariable Long userId, @RequestBody Profile profile) {
        Profile createdProfile = profileService.saveProfile(userId, profile);
        if (createdProfile != null) {
            return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{userId}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long userId, @RequestBody UpdateProfileRequest request) {
        Profile updatedProfile = profileService.updateProfile(userId, request);
        return (updatedProfile != null)
                ? new ResponseEntity<>(updatedProfile, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long userId) {
        profileService.deleteProfile(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
