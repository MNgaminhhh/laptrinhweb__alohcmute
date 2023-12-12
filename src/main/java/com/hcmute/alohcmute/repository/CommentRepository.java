package com.hcmute.alohcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import com.hcmute.alohcmute.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAll();
    Optional<Comment> findById(Long commentId);
}
