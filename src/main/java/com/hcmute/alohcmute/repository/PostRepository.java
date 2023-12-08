package com.hcmute.alohcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hcmute.alohcmute.entity.Post;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.user.id = :userId")
    List<Post> findByUserId(@Param("userId") Long userId);
    List<Post> findByContentContaining(String keyword);
}
