package com.hcmute.alohcmute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hcmute.alohcmute.entity.Profile;
import com.hcmute.alohcmute.repository.ProfileRepository;

@Service
public class ProfileService {
    
    private ProfileRepository profileRespository;
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRespository = profileRepository;
    }

    public List<Profile> getNameOfUser() {
        return profileRespository.findNameOfUser();
    }

    public Optional<Profile> getProfileUser(Long userID) {
        return profileRespository.findById(userID);
    }

    public Profile saveProfile(Profile profile) {
       return profileRespository.save(profile);
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
