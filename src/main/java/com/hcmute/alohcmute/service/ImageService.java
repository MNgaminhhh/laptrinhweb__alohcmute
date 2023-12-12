package com.hcmute.alohcmute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcmute.alohcmute.entity.Image;
import com.hcmute.alohcmute.repository.ImageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public List<Image> findAllImages() {
        return imageRepository.findAll();
    }

    public Optional<Image> findImageById(Long imageId) {
        return imageRepository.findById(imageId);
    }
    public boolean deleteImagesByUserId(Long userId) {
        List<Image> images = imageRepository.findByUserUserId(userId);
        if (!images.isEmpty()) {
            imageRepository.deleteAll(images);
            return true;
        }
        return false;
    }
    public boolean deleteImageById(Long imageId) {
        Optional<Image> optionalImage = imageRepository.findById(imageId);
        if (optionalImage.isPresent()) {
            imageRepository.deleteById(imageId);
            return true;
        }
        return false;
    }
}
