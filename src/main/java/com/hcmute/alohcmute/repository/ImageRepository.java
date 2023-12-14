package com.hcmute.alohcmute.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcmute.alohcmute.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByUserUserId(Long userId);
    void deleteById(Long imageId);
}
