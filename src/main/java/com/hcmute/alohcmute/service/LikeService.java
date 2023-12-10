package com.hcmute.alohcmute.service;

import com.hcmute.alohcmute.entity.Like;
import com.hcmute.alohcmute.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) { this.likeRepository = likeRepository; }

    public Optional<Like> getLikeById(Long likeId) { return likeRepository.findById(likeId); }

    public List<Like> getAllLikes() { return likeRepository.findAll(); }

    public Like saveLike(Like like) { return likeRepository.save(like); }

    public void deleteLike(Long likeId) { likeRepository.deleteById(likeId);}
}
