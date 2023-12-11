package com.hcmute.alohcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcmute.alohcmute.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    
}
