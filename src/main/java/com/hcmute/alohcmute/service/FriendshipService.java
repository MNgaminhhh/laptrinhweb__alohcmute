package com.hcmute.alohcmute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcmute.alohcmute.entity.Friendship;
import com.hcmute.alohcmute.entity.User;
import com.hcmute.alohcmute.enums.FriendshipStatus;
import com.hcmute.alohcmute.repository.FriendshipRepository;
import com.hcmute.alohcmute.repository.UserRepository;

@Service
public class FriendshipService {
    
    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Friendship> getAllFriendship() {
        return friendshipRepository.findAll();
    }
    public List<Friendship> getFriendshipByStatus(FriendshipStatus status) {
        return friendshipRepository.findByStatus(status);
    }

    public List<Friendship> getFriendshipOfUser(Long userId, FriendshipStatus status) {
        return friendshipRepository.findFriendshipOfUser(userId, status);
    }

    public Optional<Friendship> findRelationship(Long userId1, Long userId2) {
        return friendshipRepository.findRelationship(userId1, userId2);
    }

    public Friendship addFriendship(Long userId1, Long userId2, Friendship friendship) {
        Optional<User> user1 = userRepository.findById(userId1);
        Optional<User> user2 = userRepository.findById(userId2);
        if (user1.isPresent() && user2.isPresent()) {
            friendship.setUser1(user1.get());
            friendship.setUser2(user2.get());
            return friendshipRepository.save(friendship);
        } else {
            return null;
        }
    }

    public void deleteFriendship(Long userId1, Long userId2) {
        Optional<Friendship> friendship = friendshipRepository.findRelationship(userId1, userId2);
        if (friendship.isPresent()) {
            friendshipRepository.delete(friendship.get());
        }
    }

    public Friendship updateStatus(Long userId1, Long userId2, FriendshipStatus status) {
        Optional<Friendship> oldFriendship = friendshipRepository.findRelationship(userId1, userId2);
        Friendship newFriendship = oldFriendship.get();
        if (oldFriendship.isPresent()) {
            newFriendship.setStatus(status);
            return friendshipRepository.save(newFriendship);    
        } else {
            return null;
        }
    }

}
