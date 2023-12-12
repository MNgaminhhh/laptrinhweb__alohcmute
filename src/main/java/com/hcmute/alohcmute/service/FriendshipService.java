package com.hcmute.alohcmute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hcmute.alohcmute.entity.Friendship;
import com.hcmute.alohcmute.enums.FriendshipStatus;
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

    public Optional<Friendship> getFriendshipByID(Long id){
        return friendshipRepository.findById(id);
    }

    public List<Friendship> getFriendshipByStatus(FriendshipStatus status) {
        return friendshipRepository.findByStatus(status);
    }

    public List<Friendship> getFriedshipOfUser(Long userId, FriendshipStatus status) {
         return friendshipRepository.findFriendshipOfUser(userId, status);
    }

    public Friendship addFriendship(Friendship friendship) {
        return friendshipRepository.save(friendship);
    }

    public void deleteFriendship(Long id) {
        friendshipRepository.deleteById(id);
    }

    public void editFriendship(Long id, Friendship newFriendship) {
        if (friendshipRepository.existsById(id)) {
            newFriendship.setFriendshipId(id);
            friendshipRepository.save(newFriendship);
        }
    }

}
