package com.hcmute.alohcmute.repository;

import com.hcmute.alohcmute.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAll();
    Optional<Comment> findById(Long commentId);
    void deleteById(Long commentId);
    // Các truy vấn cụ thể có thể được thêm ở đây nếu cần
}
