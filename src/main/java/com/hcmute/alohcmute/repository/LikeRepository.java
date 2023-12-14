package com.hcmute.alohcmute.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcmute.alohcmute.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPost_PostIdAndUser_UserId(Long postId, Long userId);
    Optional<Like> findByUser_UserIdAndPost_PostId(Long userId, Long postId);
}
