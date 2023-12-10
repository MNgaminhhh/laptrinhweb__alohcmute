package com.hcmute.alohcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hcmute.alohcmute.entity.Like;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    // Các truy vấn cụ thể có thể được thêm ở đây nếu cần
    List<Like> finaAll();
    Optional<Like> findById(Long likeId);
    void  deleteById(Long likeId);
}
