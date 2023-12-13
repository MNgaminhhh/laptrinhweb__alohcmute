package com.hcmute.alohcmute.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hcmute.alohcmute.entity.Profile;
import com.hcmute.alohcmute.enums.FriendshipStatus;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("SELECT p FROM Profile p " +
       "WHERE p.userId!=:userId AND p.userId NOT IN (" +
       "   SELECT f.user2.userId FROM Friendship f " +
       "   WHERE f.user1.userId = :userId" +
       ")")
    public List<Profile> findProfileOfUserNotBeFriend(@Param ("userId") Long userId1);

    @Query("SELECT p FROM Profile p " +
       "WHERE p.userId!=:userId AND p.userId IN (" +
       "   SELECT f.user2.userId FROM Friendship f " +
       "   WHERE f.user1.userId = :userId AND f.status= :status" +
       ")")
    public List<Profile> findProfileFriend(@Param ("userId") Long userId1, @Param ("status") FriendshipStatus status);

    @Query("SELECT p FROM Profile p " +
      "WHERE p.userId!= :userId AND "+
      "(p.userId IN (SELECT m.sender.userId FROM Message m WHERE m.receiver.userId = :userId)"+ 
      " OR p.userId IN (SELECT m.receiver.userId FROM Message m WHERE m.sender.userId= :userId))")
    public List<Profile> findProfileMessage(@Param ("userId") Long receiverId);
}
