package com.hcmute.alohcmute.apicontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcmute.alohcmute.entity.Like;
import com.hcmute.alohcmute.service.LikeService;
@RestController
@RequestMapping("/api/like")
public class ApiLikeController {
    private final LikeService likeService;

    @Autowired
    public ApiLikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/{postId}")
    public Like likePost(@PathVariable Long postId) {
        // Assume you have a method in your service to handle liking a post
        return likeService.likePost(postId);
    }

    @DeleteMapping("/{postId}")
    public void unlikePost(@PathVariable Long postId) {
        // Assume you have a method in your service to handle unliking a post
        likeService.unlikePost(postId);
    }
}
