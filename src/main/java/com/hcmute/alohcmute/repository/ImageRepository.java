package com.hcmute.alohcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcmute.alohcmute.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    // Các truy vấn cụ thể có thể được thêm ở đây nếu cần
}
