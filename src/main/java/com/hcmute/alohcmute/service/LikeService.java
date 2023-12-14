package com.hcmute.alohcmute.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcmute.alohcmute.Dto.LikeDto;
import com.hcmute.alohcmute.entity.Comment;
import com.hcmute.alohcmute.entity.Like;
import com.hcmute.alohcmute.entity.Post;
import com.hcmute.alohcmute.entity.User;
import com.hcmute.alohcmute.repository.LikeRepository;
import com.hcmute.alohcmute.repository.PostRepository;
import com.hcmute.alohcmute.repository.UserRepository;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private UserRepository userRepository;
    public boolean isPostLikedByUser(Long userId, Long postId) {
        Optional<Like> likeOptional = likeRepository.findByUser_UserIdAndPost_PostId(userId, postId);
        return likeOptional.isPresent();
    }
    public ResponseEntity<LikeDto> likePost(LikeDto likeDto) {
        Optional<Post> postOptional = postRepository.findById(likeDto.getPostId());
        Optional<User> userOptional = userRepository.findById(likeDto.getUserId());

        if (postOptional.isPresent() && userOptional.isPresent()) {
            Post post = postOptional.get();
            User user = userOptional.get();

            Like newLike = new Like();
            newLike.setPost(post);
            newLike.setUser(user);
            newLike.setCreatedAt(LocalDateTime.now());
            likeRepository.save(newLike);
            return ResponseEntity.ok(likeDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    public ResponseEntity<String> unlikePost(Long postId, Long userId) {
        Optional<Like> likeOptional = likeRepository.findByPost_PostIdAndUser_UserId(postId, userId);

        if (likeOptional.isPresent()) {
            likeRepository.deleteById(likeOptional.get().getLikeId());
            return ResponseEntity.ok("Like deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Like not found");
        }
    }

}
