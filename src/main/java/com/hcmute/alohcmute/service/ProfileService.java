package com.hcmute.alohcmute.service;

import java.util.Optional;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcmute.alohcmute.Dto.UpdateProfileRequest;
import com.hcmute.alohcmute.entity.Profile;
import com.hcmute.alohcmute.entity.User;
import com.hcmute.alohcmute.enums.FriendshipStatus;
import com.hcmute.alohcmute.enums.Gender;
import com.hcmute.alohcmute.repository.ProfileRepository;
import com.hcmute.alohcmute.repository.UserRepository;

@Service
public class ProfileService {
    
    @Autowired
    private UserRepository userRepository;
    
    private ProfileRepository profileRespository;
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRespository = profileRepository;
    }

    public List<Profile> findAllProfiles() {
        return profileRespository.findAll();
    }

    public Optional<Profile> findProfileById(Long userID) {
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

    public Profile saveProfile(Long userId, Profile profile) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            profile.setUser(user);
            return profileRespository.save(profile);
        } else {
            return null;
        }
    }

    public Profile updateProfile(Long userId, UpdateProfileRequest request) {
    Optional<Profile> existingProfile = profileRespository.findById(userId);

        if (existingProfile.isPresent()) {
            Profile profile = existingProfile.get();
            profile.setFirstName(request.getFirstName());
            profile.setLastName(request.getLastName());
            profile.setDateOfBirth(LocalDate.parse(request.getDateOfBirth()));
            profile.setGender(Gender.valueOf(request.getGender()));
            profile.setBio(request.getBio());

            return profileRespository.save(profile);
        } else {
            return null;
        }
    }

    public void deleteProfile(Long userId) {
        profileRespository.deleteById(userId);
    }
}
