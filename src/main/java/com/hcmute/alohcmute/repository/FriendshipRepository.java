package com.hcmute.alohcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcmute.alohcmute.entity.Friendship;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    // Các truy vấn cụ thể có thể được thêm ở đây nếu cần
}
