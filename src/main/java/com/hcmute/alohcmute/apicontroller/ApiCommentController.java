package com.hcmute.alohcmute.apicontroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcmute.alohcmute.Dto.CommentDto;
import com.hcmute.alohcmute.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class ApiCommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<CommentDto> addCommentToPost(@RequestBody CommentDto commentDto) {
        return commentService.addCommentToPost(commentDto);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long commentId, @RequestBody CommentDto updatedCommentDto) {
        return commentService.updateComment(commentId, updatedCommentDto);
    }
    
}
