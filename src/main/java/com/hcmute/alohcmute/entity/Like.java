package com.hcmute.alohcmute.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Likes")
public class Like {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_ID")
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "user_ID", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_ID")
    @JsonBackReference
    private Post post;

    @Column(name = "created_At")
    private LocalDateTime createdAt;
}
