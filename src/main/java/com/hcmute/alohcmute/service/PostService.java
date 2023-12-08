package com.hcmute.alohcmute.service;

import com.hcmute.alohcmute.entity.Post;
import com.hcmute.alohcmute.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }

    public Post createPost(Post post) {
        // Add any business logic/validation before saving the post
        return postRepository.save(post);
    }

    public Post updatePost(Long postId, Post updatedPost) {
        if (postRepository.existsById(postId)) {
            updatedPost.setPostId(postId);
            return postRepository.save(updatedPost);
        } else {
            return null;
        }
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    // Add additional methods as needed for your business logic
}
