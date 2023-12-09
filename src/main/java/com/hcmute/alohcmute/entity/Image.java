package com.hcmute.alohcmute.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_ID")
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "user_ID", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_ID", nullable = false)
    @JsonIgnore
    private Post post;

    @Column(name = "file_Path", nullable = false)
    private String filePath;

    @Column(name = "created_At")
    private LocalDateTime createdAt;

}
