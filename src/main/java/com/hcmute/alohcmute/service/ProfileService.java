package com.hcmute.alohcmute.service;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hcmute.alohcmute.entity.Profile;
import com.hcmute.alohcmute.enums.FriendshipStatus;
import com.hcmute.alohcmute.repository.ProfileRepository;

@Service
public class ProfileService {
    
    private ProfileRepository profileRespository;
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRespository = profileRepository;
    }

    public Optional<Profile> getProfileUser(Long userID) {
        return profileRespository.findById(userID);
    }

    public List<Profile> getProfileUserNotBeFriend(Long userId1) {
        return profileRespository.findProfileOfUserNotBeFriend(userId1);
    }

    public List<Profile> getProfileFriend(Long userId1, FriendshipStatus status) {
        return profileRespository.findProfileFriend(userId1, status);
    }

    public List<Profile> getProfileOfMessageSenderOrReceiver(Long userId) {
        return profileRespository.findProfileMessage(userId);
    }

    public void deleteById(Long userId) {
        profileRespository.deleteById(userId);
    }

    public Profile eidtProfile(Long userId, Profile newProfile) {
        if (profileRespository.existsById(userId)) {
            newProfile.setUserId(userId);
            return profileRespository.save(newProfile);
        } 
        else {
            return null;
        }
    }
}
