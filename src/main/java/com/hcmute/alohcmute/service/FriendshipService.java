package com.hcmute.alohcmute.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hcmute.alohcmute.entity.Friendship;
import com.hcmute.alohcmute.repository.FriendshipRepository;

@Service
public class FriendshipService {
    
    private final FriendshipRepository friendshipRepository;

    public FriendshipService(FriendshipRepository friendshipRepository) {
        this.friendshipRepository = friendshipRepository;
    }

    public List<Friendship> getAllFriendship() {
        return friendshipRepository.findAll();
    }
}
