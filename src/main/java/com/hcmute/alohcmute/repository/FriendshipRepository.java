package com.hcmute.alohcmute.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hcmute.alohcmute.entity.Friendship;
import com.hcmute.alohcmute.enums.FriendshipStatus;
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    
    List<Friendship> findAll();
    List<Friendship> findByStatus(FriendshipStatus status);

    @Query("SELECT f FROM Friendship f WHERE f.status = :status AND f.user1.userId = :userId")
    List<Friendship> findFriendshipOfUser(@Param("userId") Long userId, @Param("status") FriendshipStatus status);

    @Query("SELECT f FROM Friendship f WHERE f.user1.userId=:userId1 AND f.user2.userId=:userId2")
    Optional<Friendship> findRelationship(@Param("userId1") Long userId1, @Param("userId2") Long userId2);
}
