package com.hcmute.alohcmute.service;
import com.hcmute.alohcmute.entity.Comment;
import com.hcmute.alohcmute.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private  final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) { this.commentRepository = commentRepository; }

    public  Optional<Comment> getCommentById(Long commentId) { return commentRepository.findById(commentId); }

    public List<Comment> getAllComments() {return commentRepository.findAll(); }

    public Comment saveComment(Comment comment) { return commentRepository.save(comment); }

    public void deleteComment(Long commentId) { commentRepository.deleteById(commentId); }
}
