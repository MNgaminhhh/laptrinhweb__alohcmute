package com.hcmute.alohcmute.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcmute.alohcmute.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
    // Các truy vấn cụ thể có thể được thêm ở đây nếu cần
}
