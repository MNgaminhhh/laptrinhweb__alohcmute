package com.hcmute.alohcmute.apicontroller;

import com.hcmute.alohcmute.Dto.PostWithImageDto;
import com.hcmute.alohcmute.entity.Image;
import com.hcmute.alohcmute.entity.Post;
import com.hcmute.alohcmute.entity.User;
import com.hcmute.alohcmute.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class ApiPostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public Optional<Post> getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody Post updatedPost) {
        return postService.updatePost(postId, updatedPost);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
    @PostMapping("/createWithImage")
    public ResponseEntity<Post> createPostWithImage(@RequestBody PostWithImageDto postWithImageDto) {
        Long userId = postWithImageDto.getUserId();
        Post post = new Post();
        post.setContent(postWithImageDto.getContent());
        post.setCreatedAt(LocalDateTime.now());
        User user = new User();
        user.setUserId(userId);
        post.setUser(user);
        Post savedPost = postService.createPost(post);

        Image image = new Image();
        image.setFilePath(postWithImageDto.getImageUrl());
        image.setCreatedAt(LocalDateTime.now());
        user.setUserId(userId);
        image.setUser(user);
        image.setPost(savedPost);

        postService.saveImage(image);

        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }
}
