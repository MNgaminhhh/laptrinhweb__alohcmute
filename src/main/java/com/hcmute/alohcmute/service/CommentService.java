package com.hcmute.alohcmute.service;

import com.hcmute.alohcmute.Dto.CommentDto;
import com.hcmute.alohcmute.entity.Comment;
import com.hcmute.alohcmute.entity.Post;
import com.hcmute.alohcmute.entity.User;
import com.hcmute.alohcmute.repository.CommentRepository;
import com.hcmute.alohcmute.repository.PostRepository;
import com.hcmute.alohcmute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<CommentDto> addCommentToPost(CommentDto commentDto) {

        Optional<Post> postOptional = postRepository.findById(commentDto.getPostId());
        Optional<User> userOptional = userRepository.findById(commentDto.getUserId());

        if (postOptional.isPresent() && userOptional.isPresent()) {
            Post post = postOptional.get();
            User user = userOptional.get();

            Comment comment = new Comment();
            comment.setPost(post);
            comment.setUser(user);
            comment.setContent(commentDto.getContent());
            comment.setCreatedAt(LocalDateTime.now());

            commentRepository.save(comment);
            return ResponseEntity.ok(commentDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    public ResponseEntity<String> deleteComment(Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);

        if (commentOptional.isPresent()) {
            commentRepository.deleteById(commentId);
            return ResponseEntity.ok("Comment deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found");
        }
    }

    public ResponseEntity<CommentDto> updateComment(Long commentId, CommentDto updatedCommentDto) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);

        if (commentOptional.isPresent()) {
            Comment existingComment = commentOptional.get();
            existingComment.setContent(updatedCommentDto.getContent());
            commentRepository.save(existingComment);
            return ResponseEntity.ok(updatedCommentDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
