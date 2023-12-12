package com.hcmute.alohcmute.apicontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hcmute.alohcmute.entity.Image;
import com.hcmute.alohcmute.service.ImageService;

@RestController
@RequestMapping("/api/images")
public class ApiImageeController {

    private final ImageService imageService;

    @Autowired
    public ApiImageeController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public List<Image> getAllImages() {
        return imageService.findAllImages();
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<Image> getImageById(@PathVariable Long imageId) {
        return imageService.findImageById(imageId)
                .map(image -> new ResponseEntity<>(image, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{imageId}")
    public ResponseEntity<String> deleteImage(@PathVariable Long imageId) {
        if (imageService.deleteImageById(imageId)) {
            return new ResponseEntity<>("Image deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Image not found", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/deleteByUserId/{userId}")
    public ResponseEntity<String> deleteImagesByUserId(@PathVariable Long userId) {
        if (imageService.deleteImagesByUserId(userId)) {
            return new ResponseEntity<>("Images deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No images found for the user", HttpStatus.NOT_FOUND);
        }
    }
}
