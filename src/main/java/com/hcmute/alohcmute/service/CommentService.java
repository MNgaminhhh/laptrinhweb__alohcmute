package com.hcmute.alohcmute.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.hcmute.alohcmute.entity.Comment;
import com.hcmute.alohcmute.repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    
    public List<Comment> getAllComments() 
    { return commentRepository.findAll(); }
    
    public Optional<Comment> getCommentById(Long commentId) 
    { return commentRepository.findById(commentId); }
}
