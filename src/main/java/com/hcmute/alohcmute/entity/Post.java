package com.hcmute.alohcmute.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_ID")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "user_ID", nullable = false)
    private User user;

    @Column(length = 5000)
    private String content;

    @Column(name = "created_At")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post")
    @JsonManagedReference  
    private List<Comment> comments;

    @OneToMany(mappedBy = "post")
    private List<Image> images;

    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    private List<Like> likes;
}
