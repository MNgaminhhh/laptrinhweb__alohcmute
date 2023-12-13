package com.hcmute.alohcmute.service;

import com.hcmute.alohcmute.entity.Profile;
import com.hcmute.alohcmute.entity.User;
import com.hcmute.alohcmute.repository.ProfileRepository;
import com.hcmute.alohcmute.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;
    public List<Profile> findAllProfiles() {
        return profileRepository.findAll();
    }

    public Optional<Profile> findProfileById(Long userId) {
        return profileRepository.findById(userId);
    }

    // public Profile saveProfile(Profile profile) {
    //     User user = new User();
    //     profile.setUser(user);
    //     return profileRepository.save(profile);
    // }
    public Profile saveProfile(Long userId, Profile profile) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            profile.setUser(user);
            return profileRepository.save(profile);
        } else {
            return null;
        }
    }

    public Profile updateProfile(Long userId, Profile updatedProfile) {
        Optional<Profile> existingProfile = profileRepository.findById(userId);
        
        if (existingProfile.isPresent()) {
            updatedProfile.setUserId(userId);
            return profileRepository.save(updatedProfile);
        } else {
            return null;
        }
    }
    public void deleteProfile(Long userId) {
        profileRepository.deleteById(userId);
    }
}
