package com.hcmute.alohcmute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hcmute.alohcmute.entity.Like;
import com.hcmute.alohcmute.repository.LikeRepository;

@Service
public class LikeService {
        private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }
    
    public List<Like> getAllLikes() 
    { return likeRepository.findAll(); }
    
    public Optional<Like> getLikeById(Long LikeId) 
    { return likeRepository.findById(LikeId); }
}
