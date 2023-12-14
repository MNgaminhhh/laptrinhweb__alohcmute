package com.hcmute.alohcmute.apicontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hcmute.alohcmute.Dto.LikeDto;
import com.hcmute.alohcmute.service.LikeService;

@RestController
@RequestMapping("/api/like")
public class ApiLikeController {
    private final LikeService likeService;

    @Autowired
    public ApiLikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/check-like")
    public ResponseEntity<Boolean> checkLike(@RequestParam Long userId, @RequestParam Long postId) {
        boolean isLiked = likeService.isPostLikedByUser(userId, postId);
        return ResponseEntity.ok(isLiked);
    }
    
    @PostMapping("/{postId}")
    public ResponseEntity<LikeDto> likePost(@RequestBody LikeDto likeDto) {
        return likeService.likePost(likeDto);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> unlikePost(@PathVariable Long postId, @RequestBody LikeDto likeDto) {
        return likeService.unlikePost(postId, likeDto.getUserId());
    }
}
