package com.hcmute.alohcmute.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostWithImageDto {
    private Long userId;
    private String content;
    private String imageUrl;
}
