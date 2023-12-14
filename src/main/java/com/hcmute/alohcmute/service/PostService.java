package com.hcmute.alohcmute.service;

import com.hcmute.alohcmute.entity.Image;
import com.hcmute.alohcmute.entity.Post;
import com.hcmute.alohcmute.repository.ImageRepository;
import com.hcmute.alohcmute.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ImageRepository imageRepository;
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }
    public void saveImage(Image image) {
        imageRepository.save(image);
    }

    public Post createPost(Post post) {
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

    public List<Post> getPostsByUsername(String username) {
        return postRepository.findByUserUsername(username);
    }
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public void deletePostAndImages(Post post) {
        List<Image> images = post.getImages();
        for (Image image : images) {
            imageRepository.deleteById(image.getImageId());
        }
        postRepository.delete(post);
    }
}
