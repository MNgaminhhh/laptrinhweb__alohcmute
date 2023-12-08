package com.hcmute.alohcmute.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcmute.alohcmute.entity.Friendship;
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findAll();
}
